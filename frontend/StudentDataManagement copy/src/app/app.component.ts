import { Component, NgModule, OnInit } from '@angular/core';
import { StudentService } from './student.service';
import { HttpClientModule } from '@angular/common/http';
import { FormBuilder, FormControl, FormGroup, FormsModule, NgForm, Validators } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { FilterPipe } from './FilterPipe';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatButtonModule } from '@angular/material/button';
import { MatRadioModule } from '@angular/material/radio';   
import { AnimationDurations, MatNativeDateModule } from '@angular/material/core';
import { MychartComponent } from './mychart/mychart.component';
import { RouterModule } from '@angular/router';
import {MatTableDataSource, MatTableModule} from '@angular/material/table'
import {MatSnackBar, MatSnackBarConfig} from '@angular/material/snack-bar';

@Component({
    selector: 'app-root',
    standalone: true,
    templateUrl: './app.component.html',
    styleUrls: ['./app.component.css'],
    imports: [FormsModule,
        HttpClientModule,
        CommonModule,
       FilterPipe,
       MatInputModule,
       MatFormFieldModule,
       MatButtonModule,
       MatRadioModule,
       MatNativeDateModule,
      MychartComponent,
      RouterModule,
      MatTableModule,
     
      ]
})

export class AppComponent implements OnInit {
[x: string]: any;
  title = 'StudentDataManagement';
  studentName?: string;
  fatherName?: string;
  studentID?: String;
  studentDOB?: string;
  studentGender?: string;
  studentDepartment?: string;
  studentMobileNumber?: string;
  studentParentMobileNumber?: string;
  studentEmail?: string;
  studentAddress?: string;

  studentDetails: any = [];
  fields: any[] = [];
student: any ={};
errormessage :string ='';
showError: boolean = false;
success:boolean = false;
searchtext: any;
formSubmitted: boolean = false;
fieldsToShowErrors: string[] = [];
defaultGender = 'Male';

// datasource = new MatTableDataSource(this.studentDetails);
displayedColumns : string[]= ['studentID','studentName','fatherName','studentDOB','studentGender','studentDepartment','studentMobileNumber','studentParentMobileNumber','studentEmail','studentAddress'];

showsnackbar(message: string, snackbarclass: string):void  {
  console.log(snackbarclass);
  this._snackBar.open(message,'ok',{
    duration: 2000, 
    panelClass:snackbarclass
  });
}

  constructor(private studentService: StudentService, private _snackBar: MatSnackBar) {
  }

  ngOnInit(){
    this.getStudents();
    this.formfields();
    
  
  }
  selectStudent(student: any): void {
    console.log(student);
    this.student = student;
  }

  register(registerForm: NgForm) {
    this.formSubmitted = true;
    if (registerForm.valid) {
      this.studentService.saveStudent(registerForm.value).subscribe({
        next: (response) => {
        },
        error: (error) =>{ console.error('Error:', error)
      this.showsnackbar('Failed to add Student. Plese try again', 'fail');
      },
        complete: () => {
        this.resetForm(registerForm); 
        this.showsnackbar('registration success', 'successful');
        this.getStudents();
      }
      });
    } else {
      let errorMessages = [];
      for (const controlName in registerForm.controls) {
      //  if (this.fieldsToShowErrors.includes(controlName)) {
        const control = registerForm.controls[controlName];
        if (control.errors) {
          for (const key in control.errors) {
            let message = this.getErrorMessage(controlName, key, control.errors[key]);
            errorMessages.push(message);
            console.log(errorMessages);
            console.log(controlName);
            console.log(key);
            console.log(control.errors[key]);
          }
        }
    //  }
    }
      this.showsnackbar('Please check and fill form correctly:\n' + errorMessages.join('\n'), 'fail');
    } 
  }
  
 getErrorMessage(controlName: string, errorKey: string, errorValue: any): string {
    // const prettyControlName = controlName.charAt(0).toUpperCase() + controlName.slice(1);
  const prettyControlName = controlName.replace(/([A-Z])/g, ' $1').trim();
    switch (errorKey) {
      case 'required':
        return `${prettyControlName} is required.`;
      case 'maxlength':
        return `${prettyControlName} must be no longer than ${errorValue.requiredLength} characters.`;
      case 'minlength':
        return `${prettyControlName} must be at least ${errorValue.requiredLength} characters long.`;
      case 'pattern':
        return `${prettyControlName} is not in the correct format.`;
      default:
        return `${prettyControlName} is invalid.`;
    }
  }


  getStudents() {
    this.studentService.getStudents().subscribe({
      next: (response) => {
         console.log(response);
        this.studentDetails = response;  
        this.studentService.notifyStudentLoaded(true); 
      },
      error: error => console.error('Error:', error)
    });
  }

  deleteStudent(registerForm: NgForm) {
    if(!registerForm.value.studentID){
      this.showsnackbar('studentId is required','fail');
    }else{
    if (confirm("Are you sure you want to delete this student?")) {
    this.studentService.deleteStudent(registerForm.value.studentID).subscribe({
      next: (response) => {
        this.resetForm(registerForm); 
        this.showsnackbar('Deleted succesfully','successful');
        this.getStudents(); 
      },
      error: (error) => {
        console.error('Error:', error);
      this.showsnackbar('Failed to delete student','fail');
      }
    });
    } else {
      console.error('Deletion cancelled by user.');
      this.showsnackbar('Delete cancelled by user','fail');
    }
  }
  }

  updateStudent(registerForm: NgForm) {
    if(!registerForm.valid){
      this.showsnackbar('fields cannot be empty','fail');
    }else{
    if (confirm("Are you sure you want to update ?")) {
    this.studentService.updateStudent(registerForm.value).subscribe({
      next: (response) => {
        this.resetForm(registerForm); 
        this.showsnackbar('Updated succesfully','successful');
        this.getStudents(); 
      },
      error: (error) => {
        console.error('Error:', error);
      this.showsnackbar('data not updated please try again.','fail');
      }
    });
    } else {
      console.error('No student data provided ');
      this.showsnackbar('update cancelled.','fail');
  }
}
  }

  searchStudent(registerForm: NgForm){
    if(!registerForm.value.studentID){
      this.showsnackbar('studentId is required to search','fail');
    }else{
    this.studentService.search(registerForm.value.studentID).subscribe({
      next: (response: any) => {
        console.log(response);
        if(response.length > 0){
          this.selectStudent(response[0]);
        }else{
          this.showsnackbar('No data found.',"fail");
        }
      },
      error: (error: any)=>{
        console.error('Error:',error);
        this.showsnackbar('please try again', 'fail');
      }
    });
  }
  }

  formfields(){
    this.studentService.form().subscribe({
      next: (response) => {
        console.log(response.fields);
        this.fields = response.fields;
      }, error: (error) => {
        console.error('failed to load form:',error);
        this.showsnackbar('failed to load form. Try Again!..','fail');
      }
  });
  }

  resetForm(registerForm: NgForm): void {
    this.student ={};
    this.getStudents();
    this.formSubmitted = false;
  }

}


