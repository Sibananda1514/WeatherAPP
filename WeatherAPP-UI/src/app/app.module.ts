import { DragDropModule } from '@angular/cdk/drag-drop';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { MatCardModule } from '@angular/material/card';
import { MatIconModule } from '@angular/material/icon';
import { MatListModule } from '@angular/material/list';
import { MatSliderModule } from '@angular/material/slider';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AutocompleteLibModule } from 'angular-ng-autocomplete';
import { NgHttpLoaderModule } from 'ng-http-loader';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { MainLocationComponent } from './shared/components/main-location/main-location.component';
import { NexthourforecastComponent } from './shared/components/nexthourforecast/nexthourforecast.component';
import { WeatherBodyComponent } from './shared/components/weather-body/weather-body.component';
import { WeatherFooterComponent } from './shared/components/weather-footer/weather-footer.component';
import { WeatherHeaderComponent } from './shared/components/weather-header/weather-header.component';
import { WeatherHomeComponent } from './shared/components/weather-home/weather-home.component';

@NgModule({
  declarations: [
    AppComponent,
    WeatherHomeComponent,
    NexthourforecastComponent,
    WeatherHeaderComponent,
    WeatherFooterComponent,
    WeatherBodyComponent,
    MainLocationComponent,
  ],
  imports: [
    CommonModule,
    BrowserModule,
    BrowserAnimationsModule,
    HttpClientModule,
    AppRoutingModule,
    MatCardModule,
    MatSliderModule,
    DragDropModule,
    MatListModule,
    NgHttpLoaderModule.forRoot(),
    AutocompleteLibModule,
    MatIconModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
