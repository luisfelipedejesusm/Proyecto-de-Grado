import { Deserializable } from "./deserializable.model";

export class Role implements Deserializable{
    
    id!: Number;
    name!: String;

    deserialize(input: any): this {
        Object.assign(this, input);
        return this;
    }

}