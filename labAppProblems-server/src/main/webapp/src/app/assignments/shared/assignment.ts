import {Student} from "../../students/shared/student";
import {Problem} from "../../problems/shared/problem";
export class Assignment {
  id: number;
  student: Student;
  problem: Problem;
  grade: number;

  constructor (student: Student, problem: Problem) {
    this.student=student;
    this.problem=problem;
  }
}
