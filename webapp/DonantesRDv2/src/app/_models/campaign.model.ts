import { Deserializable } from "./deserializable.model";

export class Campaign implements Deserializable{
    
    id!: number;
    name!: string;
    campaignType!: string;
    description!: string;
    photoUrl: string = "../../../assets/images/placeholder.jpeg";

    bloodType: string = "";
    target!: number;
    expiration!: string;

    donationsReceived!: number;

    isOpen: boolean = true;


    deserialize(input: any): this {
        Object.assign(this, input);
        return this;
    }
}