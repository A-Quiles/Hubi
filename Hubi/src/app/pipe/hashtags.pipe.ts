import { Pipe, PipeTransform } from '@angular/core';
import { DomSanitizer, SafeHtml } from '@angular/platform-browser';

@Pipe({
  name: 'hashtags',
  standalone: true
})
export class HashtagsPipe implements PipeTransform {

  constructor(private sanitizer: DomSanitizer) {}

  transform(value: string): SafeHtml {
    if (!value) return value;
    // Reemplazar todos los hashtags
    const transformed = value.replace(/(#[\w]+)/g, '<span class="font-bold">$1</span>');
    // Sanitizamos para evitar advertencias de Angular
    return this.sanitizer.bypassSecurityTrustHtml(transformed);
  }
}
