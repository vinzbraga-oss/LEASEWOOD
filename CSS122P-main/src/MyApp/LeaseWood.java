package MyApp;
import MyLib.Property;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.*;
import java.time.LocalDate;
import java.util.*;
import java.util.List;

public class LeaseWood {
    static final String DATA_FILE = "leasewood-test.ser";
    static Data data; 
    static MyLib.Data createMyAppData() {
    MyLib.Data result = new MyLib.Data();

    for (Property p : data.properties) {
        result.properties.add(convertProperty(p));
    }

    for (Buyer b : data.buyers) {
        MyLib.Buyer copy = new MyLib.Buyer(b.email, b.name, b.password);
        copy.agent = b.agent;
        result.buyers.add(copy);
    }

    for (Request r : data.requests) {
        MyLib.Request copy = new MyLib.Request(r.buyer, r.property, r.type);
        copy.status = r.status;
        copy.agent = r.agent;
        result.requests.add(copy);
    }

    for (Message m : data.messages) {
        MyLib.Message copy = new MyLib.Message(m.from, m.to, m.text);
        copy.time = m.time;
        result.messages.add(copy);
    }

    for (Meeting m : data.meetings) {
        result.meetings.add(new MyLib.Meeting(m.buyer, m.agent, m.date, m.notes)
        );
    }

    for (Sale s : data.sales) {
        result.sales.add(new MyLib.Sale(s.property, s.buyer, s.agent, s.tcp, s.paid)
        );
    }

    result.wallets.putAll(data.wallets);

    for (String key : data.notifications.keySet()) {
        result.notifications.put(
            key,
            new ArrayList<>(data.notifications.get(key))
        );
    }

    return result;
}
    static JFrame frame;
    static String user, role;
    static JPanel content;
    static Color navy = new Color(28,47,65), gold = new Color(179,141,84);

    static class Property implements Serializable {
        int block, lot; double area, floor, price; String status="Available"; boolean listed=true; String buyer="";
        Property(int b,int l,double a,double f,double p){block=b;lot=l;area=a;floor=f;price=p;}
        String id(){return "Block " + block + " - Lot " + lot;}
    }
    static MyLib.Property convertProperty(Property oldProperty) {
    MyLib.Property newProperty = new MyLib.Property(
            oldProperty.block,
            oldProperty.lot,
            oldProperty.area,
            oldProperty.floor,
            oldProperty.price
    );

    newProperty.status = oldProperty.status;
    newProperty.listed = oldProperty.listed;
    newProperty.buyer = oldProperty.buyer;

    return newProperty;
}
    static class Buyer implements Serializable {
        String email,name,password,agent="";
        Buyer(String e,String n,String p){email=e;name=n;password=p;}
    }
    static class Request implements Serializable {
        String buyer, property, status="Pending", agent="", type;
        Request(String b,String p,String t){buyer=b;property=p;type=t;}
    }
    static class Message implements Serializable {
        String from,to,text,time;
        Message(String f,String t,String m){from=f;to=t;text=m;time=java.time.LocalDateTime.now().toString().replace('T',' ').substring(0,16);}
    }
    static class Meeting implements Serializable {
        String buyer,agent,date,notes;
        Meeting(String b,String a,String d,String n){buyer=b;agent=a;date=d;notes=n;}
    }
    static class Sale implements Serializable {
        String property,buyer,agent; double tcp,paid;
        Sale(String p,String b,String a,double t,double d){property=p;buyer=b;agent=a;tcp=t;paid=d;}
    }
    static class Data implements Serializable {
        ArrayList<Property> properties=new ArrayList<>();
        ArrayList<Buyer> buyers=new ArrayList<>();
        ArrayList<Request> requests=new ArrayList<>();
        ArrayList<Message> messages=new ArrayList<>();
        ArrayList<Meeting> meetings=new ArrayList<>();
        ArrayList<Sale> sales=new ArrayList<>();
        HashMap<String,Double> wallets=new HashMap<>();
        HashMap<String,ArrayList<String>> notifications=new HashMap<>();
    }
    static void save(){try(ObjectOutputStream out=new ObjectOutputStream(new FileOutputStream(DATA_FILE))){out.writeObject(data);}catch(Exception e){error("Could not save data: "+e.getMessage());}}
    static void load() {
    try (ObjectInputStream in =
            new ObjectInputStream(new FileInputStream(DATA_FILE))) {

        data = (Data) in.readObject();

    } catch (FileNotFoundException e) {
        data = new Data();

        for (int b = 1; b <= 5; b++) {
            for (int l = 1; l <= 20; l++) {
                double a = (l == 1 || l == 10 || l == 11 || l == 20)
                        ? 96 : 110;
                double f = a == 96 ? 84 : 131;

                data.properties.add(
                        new Property(b, l, a, f,
                                a == 96 ? 7654440 : 9444400)
                );
            }
        }

        for (int i = 1; i <= 3; i++) {
            data.wallets.put("Agent" + i, 0.0);
        }

        save();

    } catch (Exception e) {
        System.err.println("DATA LOADING ERROR: " + e.getMessage());
        e.printStackTrace();
        throw new RuntimeException("Could not load saved data.", e);
    }
}
    static Buyer buyer(String email){for(Buyer b:data.buyers)if(b.email.equalsIgnoreCase(email))return b;return null;}
    static Property property(String id){for(Property p:data.properties)if(p.id().equals(id))return p;return null;}
    static void notifyUser(String id,String msg){data.notifications.computeIfAbsent(id,k->new ArrayList<>()).add(0,msg);}
    static String money(double d){return String.format("PHP %,.2f",d);}
    static void error(String m){JOptionPane.showMessageDialog(frame,m,"LEASE-WOOD",JOptionPane.ERROR_MESSAGE);}
    static void info(String m){JOptionPane.showMessageDialog(frame,m,"LEASE-WOOD",JOptionPane.INFORMATION_MESSAGE);}
    static JButton button(String s,Runnable r){JButton b=new JButton(s);b.setFocusPainted(false);b.setBackground(navy);b.setForeground(Color.WHITE);b.setFont(new Font("SansSerif",Font.BOLD,13));b.addActionListener(e->r.run());return b;}
    static JLabel label(String s,int size){JLabel l=new JLabel(s);l.setFont(new Font("SansSerif",Font.BOLD,size));l.setForeground(navy);return l;}
    static JPanel panel(){JPanel p=new JPanel();p.setBackground(Color.WHITE);return p;}
    static void start(){frame=new JFrame("LEASE-WOOD | Real Estate Management System");frame.setSize(1180,760);frame.setMinimumSize(new Dimension(950,650));frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);frame.setLocationRelativeTo(null);login();frame.setVisible(true);}
    static void login(){
        JPanel root=panel();root.setLayout(new GridBagLayout());JPanel form=panel();form.setPreferredSize(new Dimension(420,370));form.setLayout(new GridLayout(0,1,8,8));form.setBorder(BorderFactory.createEmptyBorder(22,30,22,30));
        form.add(label("LEASE-WOOD ESTATE",27));form.add(new JLabel("Log in to your account"));JTextField email=new JTextField();JPasswordField pass=new JPasswordField();form.add(new JLabel("Email / Agent or Admin username"));form.add(email);form.add(new JLabel("Password"));form.add(pass);
        form.add(button("LOG IN",()->{String e=email.getText().trim(),p=new String(pass.getPassword());if(e.isEmpty()||p.isEmpty()){error("Enter your email/username and password.");return;}if(e.equals("ADMIN")&&p.equals("ADMIN123")){user=e;role="Admin";}else if((e.equals("Agent1")&&p.equals("Agent1123"))||(e.equals("Agent2")&&p.equals("Agent2234"))||(e.equals("Agent3")&&p.equals("Agent3345"))){user=e;role="Agent";}else{Buyer b=buyer(e);if(b==null||!b.password.equals(p)){error("Invalid login credentials.");return;}user=b.email;role="Buyer";}dashboard("Home");}));
        form.add(button("First time logging in? Register here",LeaseWood::register));root.add(form);frame.setContentPane(root);frame.revalidate();frame.repaint();
    }
    static void register(){JTextField email=new JTextField(),name=new JTextField();JPasswordField pass=new JPasswordField();Object[] fields={"Full legal name",name,"Email",email,"Password (minimum 6 characters)",pass};int ok=JOptionPane.showConfirmDialog(frame,fields,"Buyer Registration",JOptionPane.OK_CANCEL_OPTION);if(ok!=JOptionPane.OK_OPTION)return;String e=email.getText().trim().toLowerCase(),n=name.getText().trim(),p=new String(pass.getPassword());if(n.isEmpty()||!e.matches("^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$")||p.length()<6){error("Enter a legal name, valid email, and password with at least 6 characters.");return;}if(buyer(e)!=null){error("Email is already registered.");return;}data.buyers.add(new Buyer(e,n,p));save();info("You have successfully registered your account. Please log in.");login();}
    static void dashboard(String section){JPanel root=panel();root.setLayout(new BorderLayout());JPanel sidebar=new JPanel(new GridLayout(0,1,4,4));sidebar.setBackground(navy);sidebar.setPreferredSize(new Dimension(230,0));sidebar.setBorder(BorderFactory.createEmptyBorder(20,10,20,10));JLabel brand=new JLabel("  LEASE-WOOD");brand.setForeground(Color.WHITE);brand.setFont(new Font("SansSerif",Font.BOLD,22));sidebar.add(brand);
        String[] tabs=role.equals("Buyer")?new String[]{"Home","Buy Property","Notifications","Chat"}:role.equals("Agent")?new String[]{"Home","Buyer Requests","Client Schedule","Settled Transactions","Commission Wallet","Amortization Generator","Chat"}:new String[]{"Home","Manage Properties","Manage Agents","View Sales"};
        for(String tab:tabs){JButton b=button(tab,()->dashboard(tab));if(tab.equals(section))b.setBackground(gold);sidebar.add(b);}sidebar.add(button("Log Out",()->{user=null;role=null;login();}));root.add(sidebar,BorderLayout.WEST);
        content=panel();content.setLayout(new BorderLayout(12,12));content.setBorder(BorderFactory.createEmptyBorder(20,22,20,22));content.add(label(section+"   |   "+role+": "+user,23),BorderLayout.NORTH);root.add(content,BorderLayout.CENTER);frame.setContentPane(root);
        if(section.equals("Home"))home();else if(section.equals("Buy Property"))buyProperties();else if(section.equals("Notifications"))notifications();else if(section.equals("Chat"))chat();else if(section.equals("Buyer Requests"))requests();else if(section.equals("Client Schedule"))schedules();else if(section.equals("Settled Transactions"))transactions();else if(section.equals("Commission Wallet"))wallet();else if(section.equals("Amortization Generator"))amortization();else if(section.equals("Manage Properties"))manageProperties();else if(section.equals("Manage Agents"))manageAgents();else if(section.equals("View Sales"))viewSales();frame.revalidate();frame.repaint();}
    static void home(){JPanel p=panel();p.setLayout(new GridLayout(0,1,10,10));p.add(label("Welcome to Lease-Wood Estate",27));p.add(new JLabel("Browse property listings, manage client relationships and track transactions."));if(role.equals("Buyer")){Buyer b=buyer(user);MyLib.Buyer buyerInfo=new MyLib.Buyer(b.email,b.name,b.password);buyerInfo.agent=b.agent;p.add(new JLabel("Welcome, "+buyerInfo.name));p.add(new JLabel("Assigned agent: "+(buyerInfo.agent.isEmpty()?"Not yet assigned":buyerInfo.agent)));p.add(button("Browse Properties",()->dashboard("Buy Property")));}else if(role.equals("Agent")){p.add(new JLabel("Pending buyer requests: "+data.requests.stream().filter(r->r.status.equals("Pending")).count()));p.add(button("Review Buyer Requests",()->dashboard("Buyer Requests")));}else{p.add(new JLabel("Properties: "+data.properties.size()));p.add(new JLabel("Registered buyers: "+data.buyers.size()));p.add(button("Manage Properties",()->dashboard("Manage Properties")));}content.add(p,BorderLayout.CENTER);}
    static JTable table(String[] cols,List<String[]> rows){DefaultTableModel m=new DefaultTableModel(cols,0){public boolean isCellEditable(int r,int c){return false;}};for(String[] r:rows)m.addRow(r);JTable t=new JTable(m);t.setRowHeight(27);t.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);t.setAutoCreateRowSorter(true);return t;}
    static int selected(JTable t){int r=t.getSelectedRow();if(r<0){error("Select a row first.");return -1;}return t.convertRowIndexToModel(r);}
    static void buyProperties(){JPanel top=panel();top.setLayout(new FlowLayout(FlowLayout.LEFT));JComboBox<String> block=new JComboBox<>(new String[]{"All Blocks","Block 1","Block 2","Block 3","Block 4","Block 5"});JComboBox<String> sort=new JComboBox<>(new String[]{"Block / Lot","Lot Size: Low to High","Lot Size: High to Low","Price: Low to High","Price: High to Low"});top.add(new JLabel("Block:"));top.add(block);top.add(new JLabel("Sort by:"));top.add(sort);content.add(top,BorderLayout.NORTH);
        JPanel center=panel();center.setLayout(new BorderLayout());content.add(center,BorderLayout.CENTER);Runnable refresh=()->{center.removeAll();ArrayList<Property> props=new ArrayList<>();for(Property p:data.properties)if(p.listed&&p.status.equals("Available")&&(block.getSelectedIndex()==0||p.block==block.getSelectedIndex()))props.add(p);int mode=sort.getSelectedIndex();if(mode==1)props.sort(Comparator.comparingDouble(p->p.area));if(mode==2)props.sort((a,b)->Double.compare(b.area,a.area));if(mode==3)props.sort(Comparator.comparingDouble(p->p.price));if(mode==4)props.sort((a,b)->Double.compare(b.price,a.price));ArrayList<String[]> rows=new ArrayList<>();for(Property p:props)rows.add(new String[]{p.id(),p.area+" sqm",p.floor+" sqm",money(p.price),p.status});JTable t=table(new String[]{"Property","Lot Area","Floor Area","TCP","Status"},rows);center.add(new JScrollPane(t),BorderLayout.CENTER);center.add(button("View Property Details",()->{int i=selected(t);if(i>=0)details(props.get(i));}),BorderLayout.SOUTH);center.revalidate();center.repaint();};block.addActionListener(e->refresh.run());sort.addActionListener(e->refresh.run());refresh.run();}
    static void details(Property p){MyLib.Property propertyInfo=convertProperty(p);JDialog d=new JDialog(frame,propertyInfo.id(),true);d.setSize(650,540);d.setLocationRelativeTo(frame);JPanel all=panel();all.setBorder(BorderFactory.createEmptyBorder(18,20,18,20));all.setLayout(new BorderLayout(10,10));JPanel body=panel();body.setLayout(new GridLayout(0,1,8,8));body.add(label(propertyInfo.id(),24));body.add(new JLabel("[ PROPERTY IMAGE PLACEHOLDER — add image path here later ]"));body.add(new JLabel("[ FLOOR PLAN PLACEHOLDER — add image path here later ]"));body.add(new JLabel("Lot Area: "+propertyInfo.area+" sqm"));body.add(new JLabel("Floor Area: "+propertyInfo.floor+" sqm"));body.add(new JLabel("Total Contract Price: "+money(propertyInfo.price)));body.add(new JLabel("Status: "+propertyInfo.status));body.add(new JLabel("Sample unit details; replace with confirmed developer specifications."));all.add(body,BorderLayout.CENTER);all.add(button("Request Sample Computation",()->{for(Request r:data.requests)if(r.buyer.equals(user)&&r.property.equals(p.id())&&r.type.equals("Computation")&&!r.status.equals("Completed")){error("A computation request for this property is already open.");return;}data.requests.add(new Request(user,p.id(),"Computation"));notifyUser(user,"Sample computation request submitted for "+p.id());save();info("Request sent. An agent can accept and assist you.");d.dispose();}),BorderLayout.SOUTH);d.setContentPane(all);d.setVisible(true);}
    static void notifications(){DefaultListModel<String> model=new DefaultListModel<>();for(String n:data.notifications.getOrDefault(user,new ArrayList<>()))model.addElement(n);content.add(new JScrollPane(new JList<>(model)),BorderLayout.CENTER);}
    static void requests(){ArrayList<Request> requests=new ArrayList<>();for(Request r:data.requests)if(r.status.equals("Pending")||(r.agent.equals(user)))requests.add(r);ArrayList<String[]> rows=new ArrayList<>();for(Request r:requests){MyLib.Request requestInfo=new MyLib.Request(r.buyer,r.property,r.type);requestInfo.status=r.status;requestInfo.agent=r.agent;rows.add(new String[]{requestInfo.buyer,requestInfo.property,requestInfo.type,requestInfo.status,requestInfo.agent});}JTable t=table(new String[]{"Buyer","Property","Type","Status","Agent"},rows);content.add(new JScrollPane(t),BorderLayout.CENTER);JPanel actions=panel();actions.add(button("Accept Buyer",()->{int i=selected(t);if(i<0)return;Request r=requests.get(i);if(!r.status.equals("Pending")){error("Request is already accepted.");return;}Buyer b=buyer(r.buyer);if(!b.agent.isEmpty()&&!b.agent.equals(user)){error("Buyer is assigned to another agent. Ask admin to transfer.");return;}r.agent=user;r.status="Accepted";b.agent=user;notifyUser(b.email,"Agent "+user+" accepted your request for "+r.property);save();dashboard("Buyer Requests");}));actions.add(button("Open Chat",()->dashboard("Chat")));actions.add(button("Set Schedule",()->{int i=selected(t);if(i<0)return;Request r=requests.get(i);if(!r.agent.equals(user)){error("Accept the buyer first.");return;}createSchedule(r.buyer,user);}));content.add(actions,BorderLayout.SOUTH);}
    static void createSchedule(String b,String a){JTextField date=new JTextField(LocalDate.now().plusDays(1).toString());JTextField note=new JTextField("Property viewing");int ok=JOptionPane.showConfirmDialog(frame,new Object[]{"Date (YYYY-MM-DD)",date,"Notes",note},"Set Client Schedule",JOptionPane.OK_CANCEL_OPTION);if(ok!=JOptionPane.OK_OPTION)return;try{LocalDate day=LocalDate.parse(date.getText().trim());if(day.isBefore(LocalDate.now())){error("Choose today or a future date.");return;}for(Meeting m:data.meetings)if(m.agent.equals(a)&&m.date.equals(day.toString())){error("Agent already has a meeting on this date.");return;}data.meetings.add(new Meeting(b,a,day.toString(),note.getText().trim()));notifyUser(b,"Meeting scheduled with "+a+" on "+day);save();dashboard(role.equals("Admin")?"Manage Agents":"Client Schedule");}catch(Exception e){error("Date must be YYYY-MM-DD.");}}
    static void schedules(){ArrayList<String[]> rows=new ArrayList<>();for(Meeting m:data.meetings)if(m.agent.equals(user)){MyLib.Meeting meetingInfo=new MyLib.Meeting(m.buyer,m.agent,m.date,m.notes);rows.add(new String[]{meetingInfo.date,meetingInfo.buyer,meetingInfo.notes});}content.add(new JScrollPane(table(new String[]{"Date","Buyer","Notes"},rows)),BorderLayout.CENTER);}
    static void chat(){ArrayList<String> contacts=new ArrayList<>();if(role.equals("Buyer")){Buyer b=buyer(user);if(!b.agent.isEmpty())contacts.add(b.agent);}else for(Buyer b:data.buyers)if(b.agent.equals(user))contacts.add(b.email);JComboBox<String> who=new JComboBox<>(contacts.toArray(new String[0]));JPanel top=panel();top.add(new JLabel("Conversation with:"));top.add(who);content.add(top,BorderLayout.NORTH);JTextArea history=new JTextArea();history.setEditable(false);history.setLineWrap(true);history.setWrapStyleWord(true);content.add(new JScrollPane(history),BorderLayout.CENTER);JPanel bottom=panel();bottom.setLayout(new BorderLayout(8,8));JTextField text=new JTextField();bottom.add(text,BorderLayout.CENTER);Runnable refresh=()->{String other=(String)who.getSelectedItem();history.setText("");if(other!=null)for(Message m:data.messages)if((m.from.equals(user)&&m.to.equals(other))||(m.from.equals(other)&&m.to.equals(user))){MyLib.Message messageInfo=new MyLib.Message(m.from,m.to,m.text);messageInfo.time=m.time;history.append("["+messageInfo.time+"] "+messageInfo.from+": "+messageInfo.text+"\n\n");}};bottom.add(button("Send",()->{String other=(String)who.getSelectedItem(),msg=text.getText().trim();if(other==null){error("No agent/client assigned yet.");return;}if(msg.isEmpty()){error("Enter a message.");return;}data.messages.add(new Message(user,other,msg));notifyUser(other,"New message from "+user);save();text.setText("");refresh.run();}),BorderLayout.EAST);content.add(bottom,BorderLayout.SOUTH);who.addActionListener(e->refresh.run());refresh.run();}
    static void transactions(){ArrayList<String[]> rows=new ArrayList<>();for(Sale s:data.sales)if(s.agent.equals(user))rows.add(new String[]{s.property,s.buyer,money(s.tcp),money(s.paid)});content.add(new JScrollPane(table(new String[]{"Property","Buyer","TCP","Paid"},rows)),BorderLayout.CENTER);}
    static void wallet(){JPanel p=panel();p.setLayout(new GridLayout(0,1,10,10));p.add(label("Commission Balance: "+money(data.wallets.getOrDefault(user,0.0)),25));JTextField bank=new JTextField(),account=new JTextField(),amount=new JTextField();p.add(new JLabel("Bank Name"));p.add(bank);p.add(new JLabel("Bank Account Number"));p.add(account);p.add(new JLabel("Transfer Amount"));p.add(amount);p.add(button("Transfer to Bank (Demo)",()->{try{double a=Double.parseDouble(amount.getText());double bal=data.wallets.getOrDefault(user,0.0);if(bank.getText().trim().isEmpty()||account.getText().trim().isEmpty()||a<=0||a>bal){error("Enter bank details and a valid amount within your balance.");return;}data.wallets.put(user,bal-a);save();info("Demo transfer recorded. No real bank transfer occurred.");dashboard("Commission Wallet");}catch(Exception e){error("Enter a valid numeric amount.");}}));content.add(p,BorderLayout.CENTER);}
    static void amortization(){JPanel form=panel();form.setLayout(new GridLayout(0,2,8,8));JTextField tcp=new JTextField("9444400"),income=new JTextField("100000");JComboBox<String> financing=new JComboBox<>(new String[]{"Bank","In-house","Pag-IBIG"});form.add(new JLabel("Total Contract Price (PHP)"));form.add(tcp);form.add(new JLabel("Client Monthly Income (PHP)"));form.add(income);form.add(new JLabel("Loan Financing"));form.add(financing);JTextArea result=new JTextArea();result.setEditable(false);result.setFont(new Font("Monospaced",Font.PLAIN,14));JPanel body=panel();body.setLayout(new BorderLayout(8,8));body.add(form,BorderLayout.NORTH);body.add(new JScrollPane(result),BorderLayout.CENTER);body.add(button("Generate Sample Amortization",()->{try{double price=Double.parseDouble(tcp.getText()),salary=Double.parseDouble(income.getText());if(price<=0||salary<=0){error("TCP and monthly income must be positive.");return;}String option=(String)financing.getSelectedItem();double annual=option.equals("Bank")?.0825:option.equals("In-house")?.15:.063;double down=price*.10,principal=price-down;StringBuilder sb=new StringBuilder("ILLUSTRATIVE ESTIMATE ONLY\n\nFinancing: "+option+"\nDown payment (10%): "+money(down)+"\nLoan principal: "+money(principal)+"\nAssumed annual interest: "+(annual*100)+"%\nMax suggested payment (30% of income): "+money(salary*.30)+"\n\n");for(int years:new int[]{5,10,15,20,25,30}){if(option.equals("In-house")&&years>10)continue;double r=annual/12;int n=years*12;double payment=principal*r*Math.pow(1+r,n)/(Math.pow(1+r,n)-1);sb.append(years+" years: "+money(payment)+" / month  "+(payment<=salary*.30?"Within income guideline":"Above income guideline")+"\n");}sb.append("\nRates, loan limits, and eligibility are placeholders.\nConfirm official lender terms before using for clients.");result.setText(sb.toString());}catch(Exception e){error("Enter valid numbers for TCP and income.");}}),BorderLayout.SOUTH);content.add(body,BorderLayout.CENTER);}
    static void manageProperties(){ArrayList<String[]> rows=new ArrayList<>();for(Property p:data.properties)rows.add(new String[]{p.id(),p.area+" sqm",money(p.price),p.status,p.listed?"Listed":"Unlisted",p.buyer});JTable t=table(new String[]{"Property","Area","TCP","Status","Listing","Buyer"},rows);content.add(new JScrollPane(t),BorderLayout.CENTER);JPanel actions=panel();actions.add(button("List / Unlist",()->{int i=selected(t);if(i<0)return;Property p=data.properties.get(i);if(p.status.equals("Occupied")){error("Occupied properties cannot be relisted.");return;}p.listed=!p.listed;save();dashboard("Manage Properties");}));actions.add(button("Mark Reserved",()->{int i=selected(t);if(i<0)return;Property p=data.properties.get(i);if(!p.status.equals("Available")){error("Only available lots can be reserved.");return;}ArrayList<String> options=new ArrayList<>();for(Buyer b:data.buyers)options.add(b.email);if(options.isEmpty()){error("No registered buyers.");return;}String chosen=(String)JOptionPane.showInputDialog(frame,"Buyer with confirmed down payment:","Reserve Lot",JOptionPane.QUESTION_MESSAGE,null,options.toArray(),options.get(0));if(chosen==null)return;int confirm=JOptionPane.showConfirmDialog(frame,"Confirm down payment received for "+p.id()+"?","Confirm Reservation",JOptionPane.YES_NO_OPTION);if(confirm!=JOptionPane.YES_OPTION)return;p.status="Reserved";p.buyer=chosen;notifyUser(chosen,p.id()+" is now reserved under your account.");save();dashboard("Manage Properties");}));actions.add(button("Mark Occupied / Sold",()->{int i=selected(t);if(i<0)return;Property p=data.properties.get(i);if(!p.status.equals("Reserved")){error("Reserve the property first.");return;}String paid=JOptionPane.showInputDialog(frame,"Total payment received to date:","0");if(paid==null)return;try{double amount=Double.parseDouble(paid);if(amount<0||amount>p.price){error("Invalid payment amount.");return;}Buyer b=buyer(p.buyer);p.status="Occupied";p.listed=false;String a=b==null?"":b.agent;data.sales.add(new Sale(p.id(),p.buyer,a,p.price,amount));if(!a.isEmpty())data.wallets.put(a,data.wallets.getOrDefault(a,0.0)+p.price*.03);notifyUser(p.buyer,p.id()+" has been marked sold/occupied.");save();dashboard("Manage Properties");}catch(Exception e){error("Enter a valid payment amount.");}}));content.add(actions,BorderLayout.SOUTH);}
    static void manageAgents(){JPanel p=panel();p.setLayout(new BorderLayout(8,8));ArrayList<String[]> rows=new ArrayList<>();for(Meeting m:data.meetings)rows.add(new String[]{m.agent,m.buyer,m.date,m.notes});JTable t=table(new String[]{"Agent","Buyer","Date","Notes"},rows);p.add(new JScrollPane(t),BorderLayout.CENTER);JPanel actions=panel();actions.add(button("Edit Meeting",()->{int i=selected(t);if(i<0)return;Meeting m=data.meetings.get(i);String d=JOptionPane.showInputDialog(frame,"New date (YYYY-MM-DD):",m.date);if(d==null)return;try{LocalDate.parse(d);m.date=d;notifyUser(m.buyer,"Your meeting was updated to "+d);save();dashboard("Manage Agents");}catch(Exception e){error("Invalid date format.");}}));actions.add(button("Transfer Buyer to Agent",()->{String email=JOptionPane.showInputDialog(frame,"Buyer email to transfer:");if(email==null)return;Buyer b=buyer(email.trim());if(b==null){error("Buyer not found.");return;}String a=(String)JOptionPane.showInputDialog(frame,"New agent:","Transfer",JOptionPane.QUESTION_MESSAGE,null,new String[]{"Agent1","Agent2","Agent3"},"Agent1");if(a==null)return;String old=b.agent;b.agent=a;for(Meeting m:data.meetings)if(m.buyer.equals(b.email))m.agent=a;for(Request r:data.requests)if(r.buyer.equals(b.email)&&r.status.equals("Accepted"))r.agent=a;notifyUser(b.email,"Your agent has changed from "+old+" to "+a);save();dashboard("Manage Agents");}));p.add(actions,BorderLayout.SOUTH);content.add(p,BorderLayout.CENTER);}
    static void viewSales(){ArrayList<String[]> rows=new ArrayList<>();for(Sale s:data.sales){MyLib.Sale saleInfo=new MyLib.Sale(s.property,s.buyer,s.agent,s.tcp,s.paid);rows.add(new String[]{saleInfo.property,saleInfo.buyer,saleInfo.agent,money(saleInfo.tcp),money(saleInfo.paid),String.format("%.1f%%",100*saleInfo.paid/saleInfo.tcp),money(saleInfo.tcp-saleInfo.paid)});}content.add(new JScrollPane(table(new String[]{"Property","Buyer","Agent","TCP","Paid","Progress","Remaining"},rows)),BorderLayout.CENTER);}
    public static void main(String[] args) {
    System.out.println("Working directory: " + System.getProperty("user.dir"));
    
    load();

MyLib.Data oopData = createMyAppData();
System.out.println("MyApp.Data connected successfully!");
System.out.println("Properties loaded: " + oopData.properties.size());

SwingUtilities.invokeLater(LeaseWood::start);
}
}
