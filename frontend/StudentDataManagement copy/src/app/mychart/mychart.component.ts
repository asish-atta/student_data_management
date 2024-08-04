import { Component, OnInit } from '@angular/core';
import {Chart, registerables} from 'chart.js';
import { StudentService } from '../student.service';
import { AgePipe } from './age.pipe';
import { AppComponent } from '../app.component';
import { response } from 'express';
import { CommonModule } from '@angular/common';
Chart.register(...registerables);

@Component({
  selector: '.app-mychart',
  standalone: true,
  providers: [AgePipe],
  imports: [AgePipe, CommonModule],
  templateUrl: './mychart.component.html',
  styleUrl: './mychart.component.css'
})
export class MychartComponent implements OnInit {
[x: string]: any;

  constructor(private service: StudentService, private agePipe: AgePipe){}

  chartdata : any;  // complete data taking from table
  labeldata:any[]=[];
  realdata:any[]=[];
   colordata:any[]=[];
  //  fielddata: any[]=['0-5','6-10','11-15','16-20','21-25'];
  fielddata: any[]=['age'];  // labels shown under
   private barChart?: Chart;
private pieChart?: Chart;
private doughnutChart?: Chart;
filteredStudents: any[] = [];

  ngOnInit(): void {
    this.service.studentLoaded$.subscribe(isloaded => {
      if(isloaded){
        console.log("iterating");
        this.service.getStudents().subscribe(response => {
      this.chartdata = response;
      if (this.chartdata != null) {
        this.updateChartData();
      }
    });
    }else {
      console.log('Data loading failed.');
    }
    });
  }
  
  updateChartData() {
    let ageGroupCounts = {
      count0_5: this.initializeAgeGroupsCount(),
      count6_10: this.initializeAgeGroupsCount(),
      count11_15: this.initializeAgeGroupsCount(),
      count16_20: this.initializeAgeGroupsCount(),
      count21_25: this.initializeAgeGroupsCount(),
    };

    for (const student of this.chartdata) {
      const age = this.agePipe.transform(student.studentDOB);
      const ageGroupIndex = this.getAgeGroupIndex(age);
      if (ageGroupIndex !== -1) {
        if (age >= 0 && age <= 5) {
          ageGroupCounts.count0_5[ageGroupIndex]++;
        }else if (age >= 6 && age <= 10) {
          ageGroupCounts.count6_10[ageGroupIndex]++;
        } else if (age >= 11 && age <= 15) {
          ageGroupCounts.count11_15[ageGroupIndex]++;
        } else if (age >= 16 && age <= 20) {
          ageGroupCounts.count16_20[ageGroupIndex]++;
        } else if (age >= 21 && age <= 25) {
          ageGroupCounts.count21_25[ageGroupIndex]++;
        }
      }
    }
    this.renderchart(this.fielddata, ageGroupCounts, 'bar', 'barchart');
    // this.renderchart(this.fielddata, ageGroupCounts, 'pie', 'piechart');
    // this.renderchart(this.fielddata, ageGroupCounts, 'doughnut', 'doughnut');
  }

  initializeAgeGroupsCount(): number[] {
    return new Array(this.fielddata.length).fill(0);
  }

  getAgeGroupIndex(age: number): number {
    if (age >= 0 && age <= 25) {
      return 0;
    } 
    return -1; 
  }

  onChartClick(event: any) {
    if (this.barChart && this.barChart.data) {
      const activePoints = this.barChart.getElementsAtEventForMode(event, 'nearest', { intersect: true }, true);
      if (activePoints.length > 0) {
        const firstPoint = activePoints[0];
        const labelIndex = firstPoint.index; 
        if (this.barChart.data.labels && labelIndex < this.barChart.data.labels.length) {
          const label = this.barChart.data.labels[labelIndex];
          const ageGroupLabel = this.barChart.data.datasets[firstPoint.datasetIndex]?.label; 
  
          if (ageGroupLabel) {
            const matchResult = ageGroupLabel.match(/\d+-\d+/);
          if (matchResult) {
            const ageRange = matchResult[0].split('-').map(Number);
            this.filterStudentsByAgeGroupLabel(ageGroupLabel);
          }
          }
        }
      }
    }
  }

  filterStudentsByAgeGroupLabel(ageGroupLabel: string) {
    const matchResult = ageGroupLabel.match(/\d+-\d+/);
    if (matchResult) {
      const ageRange = matchResult[0].split('-').map(Number);
      const minAge = ageRange[0];
      const maxAge = ageRange[1];
      this.filteredStudents = this.chartdata.filter((student: { studentDOB: string; }) => {
        const age = this.agePipe.transform(student.studentDOB);
        return age >= minAge && age <= maxAge;
      });
    } else {
      console.error("No valid age range found in the label");
    }
  }

  renderchart(labeldata:any, maindata: {count0_5: number[], count6_10: number[],
    count11_15: number[], count16_20: number[],count21_25: number[]}, type:any,id:any){
   console.log("entered into renderclass");
   if (type === 'bar' && this.barChart) {
    this.barChart.destroy();
  } else if (type === 'pie' && this.pieChart) {
    this.pieChart.destroy();
  } else if (type === 'doughnut' && this.doughnutChart) {
    this.doughnutChart.destroy();
  }
 

   const newChart = new Chart(id, {
    type: type,
    data: {
      labels: labeldata,
      datasets: [
        {
         label: 'age 0-5years',
        data:maindata.count0_5,
        borderWidth: 1,
        stack: 'Stack 0',
        barThickness: 20
      },{
        label: 'age 6-10years',
        data:maindata.count6_10,
        borderWidth: 1,
        stack: 'Stack 0',
        barThickness: 20
      },{
        label: 'age 11-15years',
        data:maindata.count11_15,
        borderWidth: 1,
        stack: 'Stack 0',
        barThickness: 20
      },{
        label: 'age 16-20years',
        data:maindata.count16_20,
        borderWidth: 1,
        stack: 'Stack 0',
        barThickness: 20
      },{
        label: 'age 21-25years',
        data:maindata.count21_25,
        borderWidth: 1,
        stack: 'Stack 0',
        barThickness: 20
      }]
      
    },
    options: {
      onClick: this.onChartClick.bind(this),
      scales: {
        y: {
          stacked :true,
          beginAtZero: true
        },
        x: {
          stacked: true,
          // barThickness: 20
        }
      }
    }
  });
  if (type === 'bar') {
    this.barChart = newChart;
  } else if (type === 'pie') {
    this.pieChart = newChart;
  } else if (type === 'doughnut') {
    this.doughnutChart = newChart;
  }

  }
  
}
