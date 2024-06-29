import type Terminal from "./Terminal";
import type User from "./User";

export default interface Entry {
    uuid: string,
    accessDate: Date,
    description: string,
    terminal: Terminal,
    user: User
}