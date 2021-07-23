import { Deserializable } from "./deserializable.model";
import { Role } from "./role.model";

export class User implements Deserializable{

    id!: Number;
    name!: String;
    username!: String;
    email!: String;
    isDonor!: Boolean;
    roles!: Role[];

    deserialize(input: any): this {
        Object.assign(this, input);
        this.roles = input.roles.map((role: any) => new Role().deserialize(role));
        return this;
    }

}