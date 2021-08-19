import { Time } from "@angular/common";
import { Deserializable } from "./deserializable.model";
import { DonationCenter } from "./donation-center.model";
import { User } from "./user.model";

export class DonationAppointment implements Deserializable{

    id!: number;
    userid!: Number;
    firstName!: String;
    lastName!: String;
    email!: String;
    phoneNumber!: String;
    address!: String;
    bloodGroup!: String;
    comments!: String;
    firstTimeDonor!: Boolean;
    donationCenterId!: Number;

    donationCenter!: DonationCenter;

    dateAppointment: String | null = "";
    hourAppointment: String | null = "";

    referenceNumber!: String;

    user!: User;

    constructor(){
        this.donationCenter = new DonationCenter();
    }

    deserialize(input: any): this {
        Object.assign(this, input);
        if(input && input.donationCenter){
            this.donationCenter = new DonationCenter().deserialize(input.donationCenter);
        }
        if(input && input.user){
            this.user = new User().deserialize(input.user);
        }
        return this;
    }

    getFullName(){
        return this.firstName + " " + this.lastName;
    }

    getDateAndTime(){
        return this.dateAppointment + " " + this.hourAppointment;
    }

}