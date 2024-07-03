import type Residence from "./Residence";
import type User from "./User";

export default interface Permission {
    uuid: string,
    authorized: boolean | undefined,
    creationDate: Date,
    endDate: Date,
    startDate: Date,
    invitedUser: User,
    residence: Residence,
    resident: User    
}