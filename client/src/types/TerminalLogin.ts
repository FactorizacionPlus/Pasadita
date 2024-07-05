import type { TerminalType } from "./TerminalType";

export default interface TerminalLogin {
  type: TerminalType;
  password: string;
}
