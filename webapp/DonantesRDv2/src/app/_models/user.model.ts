import { Deserializable } from "./deserializable.model";
import { Role } from "./role.model";

export class User implements Deserializable{

    id!: Number;
    name!: String; // deprecated
    username!: String;
    email!: String;

    isDonor: Boolean = false;

    firstName: string = "";
    lastName: string = "";
    phoneNumber: string = "";
    address: string = "";
    bloodType: string = "";

    roles!: Role[];

    latitude!: number;
    longitude!: number;

    getFullName(){
        return (this.firstName || "") + " " + (this.lastName || ""); 
    }

    deserialize(input: any): this {
        Object.assign(this, input);
        this.roles = input.roles.map((role: any) => new Role().deserialize(role));
        return this;
    }

}