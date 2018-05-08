import {Student} from "../../students/shared/student";
import {Problem} from "../../problems/shared/problem";

export class Assignment {
  id: number;
  studentDto: Student;
  problemDto: Problem;
  grade: number;

  constructor(student: Student, problem: Problem) {
    this.studentDto = student;
    this.problemDto = problem;
  }
}
