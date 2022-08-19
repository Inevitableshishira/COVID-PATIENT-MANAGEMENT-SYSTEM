import java.util.*;
public class pbm
{
    public static void main(String[] args)
    {
        int q=1;
        Scanner sc = new Scanner(System.in);
        Random r=new Random();
        node obj=new node();
        System.out.println("\t\"COVID PATIENT MANAGEMENT SYSTEM\"");
        while (q!=0)//To get Infinite Loop
        {
            System.out.println("\n\"MENU\"\n1.Admit Patients\n2.Display Patient Record\n3.Calculate BMI\n4.Search Patient Record\n5.Discharge patient\n6.Close");
            System.out.println("\nEnter your choice");
            int choice = sc.nextInt();
            switch (choice)
            {
                case 2:
                    System.out.println("\n\"DISPLAYING PATIENT RECORD\"\n");
                    System.out.println("Sl.No PATIENT NAME\t\tPATIENT ID\tAGE\t\tGENDER  ");
                    System.out.println("__________________________________________________");
                    obj.PrintPatient(obj.head);
                    System.out.println("__________________________________________________");
                    break;

                case 1:
                    System.out.println("\n\"ENTER PATIENT DETAILS\"\n");
                    System.out.println("Enter Number of Patients to be Admited");
                    int n= sc.nextInt();
                    for(int i=1;i<=n;i++)
                    {
                        System.out.println("\nEnter Details of Patient "+i);
                        System.out.print("Enter Patient NAME   : ");
                        String name = sc.next();
                        int id = r.nextInt(10000000);
                        System.out.print("Patient ID is        : "+id);
                        System.out.print("\nEnter Patient AGE    : ");
                        int age=sc.nextInt();
                        System.out.print("Enter Patient GENDER : ");
                        String gender = sc.next();
                        obj.AdmitPatient(name,id,age,gender);
                    }
                    break;

                case 3:
                    System.out.print("Enter the Patient Weight (in kg) :");
                    float weight=sc.nextFloat();
                    System.out.print("\nEnter the Height (in meter)      :");
                    float height=sc.nextFloat();
                    obj.bmi(weight,height);
                    break;

                case 4:
                    System.out.print("Enter the Patient Name:");
                    String key=sc.next();
                    obj.SearchPatient(key);
                    break;

                case 5:
                    System.out.print("Enter Name of the Patient to be Discharged:");
                    String dname=sc.next();
                    node m= obj.getnode(dname);//To get Node of Patient to be Discharged
                    obj.deletenode(m);//To Delete given Node
                    break;

                case 6:
                    q = 0;
                    break;
                default:
                    System.out.println("Enter Valid choice");
                    break;
            }
        }
    }
}


class node
{
    String name;
    int id;
    int age;
    String gender;
    node prev;
    node next;
    node head;

    node(String n, int i,int a,String g)
    {
        name = n;
        id = i;
        age =a;
        gender = g;
    }

    public node()
    {

    }

    //Method to Admit Patient
    void AdmitPatient(String name, int id, int age, String gender)
    {
        node new_node = new node(name, id,age,gender);//Allocate Memory for Newnode
        node temp = head;
        new_node.next = null;
        if (head == null)//If List is Empty
        {
            new_node.prev = null;
            head = new_node;
            return;
        }
        while (temp.next != null)//If list is not Empty then Traverse the list till end to insert element at  the end
        {
            temp = temp.next;

        }
        temp.next = new_node;
        new_node.prev = temp;
    }

    //Method to Display
    void PrintPatient(node Node)
    {
        if (head == null || Node == null)//If list is Empty
        {
            System.out.println("Patient Record is Empty");
        }

        node last = null;
        int i=1;
        while (Node != null)
        {
            System.out.println(i+ ".\t\t"+Node.name+"\t\t\t"+ Node.id+"\t\t"+ Node.age+"\t\t"+ Node.gender);
            i++;
            last = Node;
            Node = Node.next;
        }
    }

//Method to Calculate BMI of the Patient
    void bmi(float weight,float height)
    {
        float B;
        B = weight / (height * height);
        System.out.println("BMI of Patient is                :" + B);
        if (B >= 18.5 && B <= 24.9)
        {
            System.out.println("\"NORMAL\"");
        }
        else if (B >= 25)
        {
            System.out.println("\"OVER WEIGHT!!!\"");
        }
        else
        {
            System.out.println("\"UNDER WEIGHT!!!\"");
        }
    }

//Method to Search a Patient Details
    void SearchPatient(String key)
    {
        int z=0;
        node temp = head;

        if (temp == null)//If list is Empty
        {
            System.out.println("Patient Not Found");
        }
        else {
            for (temp = head; temp != null; temp = temp.next)
            {
                if (Objects.equals(temp.name, key))
                {
                    z=1;
                  
                    System.out.println("Displaying Details of " + key);
                    System.out.println("*************");
                    System.out.println("Name   : " + temp.name + "\nID     : " + temp.id + "\nAge    : " + temp.age + "\nGender : " + temp.gender);
                    System.out.println("*************");
                }
            }
            if(z==0)
            {
                System.out.println("Patient Not found");
            }
        }
    }


    node getnode(String key)
    {
        node temp = head;
        int i=1;

        if (temp == null)//If list is Empty
        {
            System.out.println("Patient list is Empty ");
        }
        while(temp!=null)
        {
            if (Objects.equals(temp.name, key))
            {
                System.out.println("\nDisplaying details of patient to be Discharged ");
                System.out.println("Name   : " + temp.name + "\nID     : " + temp.id + "\nAge    : " + temp.age + "\nGender : " + temp.gender);
                break;
            }
            temp=temp.next;
            i++;
        }
        return temp;
    }

    //Method to Discharge a Patient
    node deletenode(node del)
    {
        if(head==null||del==null)//If list is Empty
        {
            return null;
        }
        if(head==del)//To Delete Head node
        {
            head=del.next;
        }
        if(del.next!=null)
        {
            del.next.prev=del.prev;
        }
        if(del.prev!=null)
        {
            del.prev.next=del.next;
        }
        return head;
    }
}