import { Component, OnInit } from '@angular/core';
import { StudentService } from '../student.service';
import { Student } from '../student';
import { Observable,Subject } from "rxjs";

import {FormControl,FormGroup,Validators} from '@angular/forms';

@Component({
  selector: 'app-student-list',
  templateUrl: './student-list.component.html',
  styleUrls: ['./student-list.component.css']
})
export class StudentListComponent implements OnInit {

 constructor(private studentservice:StudentService) { }

  studentsArray: any[] = [];
  dtOptions: DataTables.Settings = {};
  dtTrigger: Subject<any>= new Subject();


  students: Observable<Student[]>;
  student : Student=new Student();
  deleteMessage=false;
  studentlist:any;
  student1:any;
  isupdated = false;


  ngOnInit() {
    this.isupdated=false;
    this.dtOptions = {
      pageLength: 5,
      stateSave:true,
      lengthMenu:[[5, 10, 20, -1], [5, 10, 20, "All"]],
      processing: true
    };
    this.studentservice.getStudentList().subscribe(data =>{
    this.students =data;
    this.dtTrigger.next();
    })
  }

  deleteStudent(id: number) {
    this.studentservice.deleteStudent(id)
      .subscribe(
        data => {
          console.log(data);
          this.deleteMessage=true;
          this.studentservice.getStudentList().subscribe(data =>{
            this.students =data
            })
        },
        error => console.log(error));
  }


  updateStudent(id: number){
    this.studentservice.getStudent(id)
      .subscribe(
        data => {
        this.isupdated = false
          this.studentlist=[data]
        console.log(this.studentlist)
        },
        error => console.log(error));
  }

  studentupdateform=new FormGroup({
    studentId:new FormControl(),
    studentName:new FormControl(),
    studentEmail:new FormControl(),
    studentBranch:new FormControl()
  });

  updateStu(updstu){
    this.student=new Student();
   this.student.studentId=this.StudentId.value;
   this.student.studentName=this.StudentName.value;
   this.student.studentEmail=this.StudentEmail.value;
   this.student.studentBranch=this.StudentBranch.value;
   console.log(this.StudentBranch.value);


   this.studentservice.updateStudent(this.student.studentId,this.student).subscribe(
    data => {
      this.isupdated=true;
      this.studentservice.getStudentList().subscribe(data =>{
        this.students = data
        })
    },
    error => console.log(error));
  }

  get StudentName(){
    return this.studentupdateform.get('studentName');
  }

  get StudentEmail(){
    return this.studentupdateform.get('studentEmail');
  }

  get StudentBranch(){
    return this.studentupdateform.get('studentBranch');
  }

  get StudentId(){
    return this.studentupdateform.get('studentId');
  }

  changeisUpdate(){
    this.isupdated=false;
  }
}
