import { Time } from "@angular/common";
import { Deserializable } from "./deserializable.model";
import { DonationCenter } from "./donation-center.model";

export class DonationAppointment implements Deserializable{

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

    appointmentDate!: String | null;
    appointmentTime!: Time | null;

    referenceNumber!: String;

    deserialize(input: any): this {
        Object.assign(this, input);
        if(input.donationCenter){
            this.donationCenter = new DonationCenter().deserialize(input.donationCenter);
        }
        return this;
    }

    getFullName(){
        return this.firstName + " " + this.lastName;
    }

    getDateAndTime(){
        return this.appointmentDate + " " + this.appointmentTime;
    }

}