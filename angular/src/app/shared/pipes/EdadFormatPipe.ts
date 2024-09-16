import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'edadFormat'
})
export class EdadFormatPipe implements PipeTransform {

  transform(value: number): string {
    return value === 1 ? `${value} año` : `${value} años`;
  }

}
