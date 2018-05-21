import {Assignment} from "../../assignments/shared/assignment";

export class Student {
  id: number;
  registrationNumber: number;
  name: string;
  groupNumber: number;
  assignments: Assignment[];

  constructor (name: string, registrationNumber: number, groupNumber: number) {
    this.name = name;
    this.registrationNumber = registrationNumber;
    this.groupNumber = groupNumber;
  }
}
