import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import {NgIf} from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { HomeComponent } from './pages/home/home.component';

@NgModule({
  declarations: [
    
  ],
  imports: [
    FormsModule,
    NgIf,
    HttpClientModule,
    HomeComponent
  ]
})
export class AppModule { }
