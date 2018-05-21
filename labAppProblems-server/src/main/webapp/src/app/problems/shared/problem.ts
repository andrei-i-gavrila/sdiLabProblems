import {Assignment} from "../../assignments/shared/assignment";

export class Problem {
  id: number;
  title: string;
  description: string;
  assignments: Assignment[];

  constructor (title: string, description: string) {
    this.title=title;
    this.description=description;
  }
}
