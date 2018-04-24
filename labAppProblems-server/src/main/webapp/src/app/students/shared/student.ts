export class Student {
  id: number;
  registrationNumber: number;
  name: string;
  groupNumber: number;

  constructor (name: string, registrationNumber: number, groupNumber: number) {
    this.name = name;
    this.registrationNumber = registrationNumber;
    this.groupNumber = groupNumber;
  }
}
