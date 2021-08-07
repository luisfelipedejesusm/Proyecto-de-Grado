import { Deserializable } from "./deserializable.model";

export class Campaign implements Deserializable{
    
    id!: number;
    name!: string;
    campaignType!: string;
    description!: string;
    imageUrl: string = "../../../assets/images/placeholder.jpeg";

    bloodType: string = "";
    target!: number;
    expiration!: string;



    deserialize(input: any): this {
        Object.assign(this, input);
        return this;
    }
}