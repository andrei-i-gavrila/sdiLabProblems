export class Assignment {
  id: number;
  studentId: number;
  problemId: number;
  grade: number;

  constructor (studentId: number, probblemId: number) {
    this.studentId=studentId;
    this.problemId=probblemId;
  }

}
