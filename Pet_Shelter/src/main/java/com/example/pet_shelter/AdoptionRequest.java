package com.example.pet_shelter;
import java.time.LocalDate;
import java.util.Scanner;
import java.util.*;

public class AdoptionRequest {
    final int adoptionId;
    protected static int Next_ID=0;
    protected Pet adoptedPet;
    protected Adopter adopter;
    protected LocalDate adoption_Date ;
    private String adoption_status="pended";
    private static ArrayList<AdoptionRequest> requests= new ArrayList<>();
    //Reham, u will not need to make setter for this because u will send it to the admins already


    public AdoptionRequest(Adopter u, Pet p){
        adoptionId =( Next_ID++);
        adopter=u;
        adoptedPet =p; //in the GUI here it will give it the cliked pet
        adoption_Date= LocalDate.now(); //defualt
    }
    //setter for adoption_status
    public void status_setter(String s){
        adoption_status=s;
    }
    //getter for adoption_status
    public String status_getter(){
        //the adopter can call it when he wants to track his request
        return adoption_status;
    }
    //getter for the list of requests
    public ArrayList<AdoptionRequest> get_requests(){
        return requests;
    }

    //adoption request method
    public static void adop_request(Adopter u,Pet p){
        //if u want to make a new request u will need to call this function for making a new object
        AdoptionRequest newRequest=new AdoptionRequest(u,p);
        requests.add(newRequest);
        System.out.println("your request have been submitted successfully ");// pop message in the GUI
    }

    //the next method will be called on AdminUser
    public static void RequestsToAdmins(){
        Scanner in=new Scanner(System.in);
        for(AdoptionRequest i:requests) {
            System.out.println("anytime u want to break reviewing enter 1");
            if (in.nextInt() == 1) {
                break;
            }
            else {

                if (i.adoption_status.equals("rejected") ||i.adoption_status.equals("approved")) {
                    continue;}
                else if (i.adoption_status.equals("pended")) {
                    System.out.println(i);
                    System.out.println("enter approved for approving this request, rejected, and pended for rewiew it next time");
                    i.adoption_status = in.next();
                    if (i.adoption_status.equals("approved") || i.adoption_status.equals("rejected")) {
                        System.out.println("done you "+i.adoption_status+" the request"); //pop message GUI
                    }
                }
            }
        }

    }


    public void request_tracking(ArrayList<AdoptionRequest>list){
        for(AdoptionRequest i:list){
            if (i.adoption_status.equals("approved") || i.adoption_status.equals("rejected")) {
                notification(i.adoption_status);
            }

        }


    }

    public void notification(String state){
        if(state.equals("rejected")){
            System.out.println("your request has been rejected");
        } else if (state.equals("approved")) {
            System.out.println("congratulations, your request has been rejected"); //will be handled later in the GUI

        }

    }




}