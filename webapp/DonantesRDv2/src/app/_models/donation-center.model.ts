import { Deserializable } from "./deserializable.model";

export class DonationCenter implements Deserializable{
    
    id!: number;
    name!: string;
    address!: string;

    username!: string;
    email!: string;
    password!: string;

    latitude!: number;
    longitude!: number;

    deserialize(input: any): this {
        Object.assign(this, input);
        return this;
    }
}