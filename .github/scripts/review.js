const { context, getOctokit } = require('@actions/github');
const { GoogleGenerativeAI } = require('@google/generative-ai');

async function run() {
    try {
        // 1. Authenticate using your repository secrets
        const octokit = getOctokit(process.env.GITHUB_TOKEN);
        const genAI = new GoogleGenerativeAI(process.env.GEMINI_API_KEY);

        // 2. Extract repository and Pull Request information
        const { owner, repo } = context.repo;
        const pull_number = context.payload.pull_request?.number;

        if (!pull_number) {
            console.log("No pull request found. Exiting.");
            return;
        }

        // 3. Fetch the code changes (the diff)
        const { data: diff } = await octokit.rest.pulls.get({
            owner,
            repo,
            pull_number,
            mediaType: { format: 'diff' }
        });

        if (!diff) {
            console.log("No code changes found.");
            return;
        }

        // 4. Pass the diff to Gemini with a strict review prompt
        const model = genAI.getGenerativeModel({ model: "gemini-1.5-flash" });
        const prompt = `You are a strict Java code reviewer. We are building a Real Estate Management System using Java and Swing. 
        Analyze the following pull request diff for:
        - Architecture mismatches (e.g., using standalone JFrames instead of JPanels)
        - Encapsulation issues (e.g., public class attributes instead of private with getters/setters)
        - Memory leaks or unhandled exceptions
        
        Provide a concise, actionable list of necessary fixes. Do not output conversational filler.
        
        Diff:
        ${diff}`;

        const result = await model.generateContent(prompt);
        const reviewComment = result.response.text();

        // 5. Post the generated review back to GitHub as a PR comment
        await octokit.rest.issues.createComment({
            owner,
            repo,
            issue_number: pull_number,
            body: reviewComment
        });

        console.log("Review submitted successfully.");
        
    } catch (error) {
        console.error("Error during automated review:", error);
        process.exit(1);
    }
}

run();