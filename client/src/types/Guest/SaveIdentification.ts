import type {IdentifierType} from "../User/IdentifierType";

export default interface SaveIdentification{
    email:string;
    identifier:string
    identifierType: IdentifierType;
}