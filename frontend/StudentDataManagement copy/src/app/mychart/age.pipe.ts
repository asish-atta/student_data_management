import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'age',
  standalone: true
})
export class AgePipe implements PipeTransform {

  transform(labeldata: string): number {
    const currentYear = new Date().getFullYear();
    const dob = new Date(labeldata);
    const dobYear = dob.getFullYear();

    return currentYear - dobYear;
  }

}
