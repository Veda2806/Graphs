import java.util.*;
public class Graphs {
    String main;
    Map<String ,HashSet<String>> graph;
    Graphs(String main){
        this.main=main;
        this.graph=new HashMap<>();
        this.graph.put(main,new HashSet<>());
    }
    void add(String user,String friend){
        if(this.graph.containsKey(user)){
            this.graph.get(user).add(friend);
        }else{
            this.graph.put(user, new HashSet<>());
            this.graph.get(user).add(friend);
        }
        if(this.graph.containsKey(friend)){
            this.graph.get(friend).add(user);
        }else{
            this.graph.put(friend, new HashSet<>());
            this.graph.get(friend).add(user);
        }
    }
    void recommend(String user){
        Set<String> nonmutual=new HashSet<>();
        for(String friend:this.graph.get(user)){
            for(String frndsfrnd:this.graph.get(friend)){
                if(!this.graph.get(user).contains(frndsfrnd)){
                    nonmutual.add(frndsfrnd);
                }
            }
        }
        nonmutual.remove(user);
        Map<String,Integer> mutual=new HashMap<>();
        for(String friend:nonmutual){
            mutual.put(friend,findMutual(user, friend));
        }
        mutual.forEach((friend,n)->System.out.println("you have "+n+" mutual friends with "+friend));
    }
    int findMutual(String user,String friend){
        int count=0;
        for(String frnd:this.graph.get(user)){
            if(this.graph.get(friend).contains(frnd)){
                count++;
            }
        }
        return count;
    }
    void remove(String user,String friend){
        if(this.graph.containsKey(user)){
            this.graph.get(user).remove(friend);
        }else{
            this.graph.get(user).remove(friend);
        }
        if(this.graph.containsKey(friend)){
            this.graph.get(friend).remove(user);
        }else{
            this.graph.get(friend).remove(user);
        }
    }
    public static void main(String args[]){
        Scanner sc=new Scanner(System.in);
        String option;
        Graphs graph=new Graphs("rakesh");
        
        /*graph.add("rakesh","pramod");
        graph.add("rakesh","shyam");
        graph.add("srikanth","shyam");
        graph.add("shivamani","shyam");
        graph.add("pramod","srikanth");
        System.out.println(graph.graph);
        graph.recommend("rakesh");*/
        while(true){
            System.out.println(" 1.add friend\n 2.remove friend\n 3.recommend friends \n 4.quit");
            option=sc.next();
            if(option.equals("4"))break;
            String user,friend;
            switch(option){
                case "1":
                    System.out.println("enter username:");
                    user=sc.next();
                    System.out.println("enter new friend name");
                    friend=sc.next();
                    graph.add(user,friend);
                    break;
                    case "2":
                    System.out.println("enter username:");
                    user=sc.next();
                    System.out.println("enter friend name");
                    friend=sc.next();
                    graph.remove(user,friend);
                break;
                case "3":
                    System.out.println("enter username:");
                    user=sc.next();
                    graph.recommend(user);
                break;
                default:
                System.out.println("invalid choice");
            }
        }


    }
}
