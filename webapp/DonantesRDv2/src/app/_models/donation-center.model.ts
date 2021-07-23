import { Deserializable } from "./deserializable.model";

export class DonationCenter implements Deserializable{
    
    id!: number;
    name!: string;
    address!: string;

    deserialize(input: any): this {
        Object.assign(this, input);
        return this;
    }
}