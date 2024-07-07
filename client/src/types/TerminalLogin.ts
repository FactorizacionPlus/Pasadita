import type { TerminalType } from "./TerminalType";

export default interface TerminalLogin {
  terminalType: TerminalType;
  password: string;
}
