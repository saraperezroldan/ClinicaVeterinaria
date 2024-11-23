import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'horaFormato'
})
export class HoraFormatPipe implements PipeTransform {

  transform(hora: string): string {
    if (hora && hora.includes(':')) {
      return hora.substring(0, 5);
    }
    return hora;
  }

}
