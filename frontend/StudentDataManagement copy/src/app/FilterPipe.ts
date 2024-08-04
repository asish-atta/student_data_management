import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'filter',
  standalone: true
})
export class FilterPipe implements PipeTransform {
  transform(studentDetails: any[], searchtext: string): any {
    if (!searchtext) return studentDetails; 
    searchtext = searchtext.toLowerCase(); 

    return studentDetails.filter(student =>
      (student.studentID && student.studentID.toString().toLowerCase().includes(searchtext)) ||
      (student.studentName && student.studentName.toString().toLowerCase().includes(searchtext))||
      (student.fatherName && student.fatherName.toString().toLowerCase().includes(searchtext))||
      (student.studentGender && student.studentGender.toString().toLowerCase().includes(searchtext))||
      (student.studentDOB && student.studentDOB.toString().toLowerCase().includes(searchtext))||
      (student.studentMobileNumber && student.studentMobileNumber.toString().toLowerCase().includes(searchtext))||
      (student.studentParentMobileNumber && student.studentParentMobileNumber.toString().toLowerCase().includes(searchtext))||
      (student.studentDepartment && student.studentDepartment.toString().toLowerCase().includes(searchtext))||
      (student.studentEmail && student.studentEmail.toString().toLowerCase().includes(searchtext))||
      (student.studentAddress && student.studentAddress.toString().toLowerCase().includes(searchtext))

    );
}
}
