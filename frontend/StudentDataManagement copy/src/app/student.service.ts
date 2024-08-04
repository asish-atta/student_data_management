import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { NgForm } from '@angular/forms';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class StudentService {

  private studentLoaded = new BehaviorSubject<boolean>(false);
  public studentLoaded$ = this.studentLoaded.asObservable();
  notifyStudentLoaded(isLoaded: boolean): void {
    this.studentLoaded.next(isLoaded);
  }

  
  constructor(private http: HttpClient) { }

  API = 'http://localhost:8080/student';
  public saveStudent(studentData: NgForm){
    return this.http.post(this.API + '/save',studentData, { responseType: 'text' });
  }

  public getStudents(){
    return this.http.get(this.API);
  }

  deleteStudent(studentID: string){
    return this.http.delete(this.API + '/delete/' +studentID,{ responseType: 'text' });
  }

  updateStudent(studentData: NgForm){
    return this.http.put(this.API + '/update',studentData,{ responseType: 'text' } );
  }

  search(studentID: string){
    return this.http.get(this.API + '/' + studentID);
  }

  form(){
    return this.http.get<any>('assets/form.json');
  }
 

}
