import { CdkDragDrop, moveItemInArray } from '@angular/cdk/drag-drop';
import { Component, EventEmitter, OnInit, Output, ViewChild } from '@angular/core';
import { LocationService } from '../../services/location.service';
import { SharedDataService } from '../../services/shared-data.service';
import { WeatherService } from '../../services/weather.service';

@Component({
  selector: 'app-main-location',
  templateUrl: './main-location.component.html',
  styleUrls: ['./main-location.component.css']
})
export class MainLocationComponent implements OnInit {
  allLocationsData: any;
  searchKey = 'cityName';
  notFoundText = 'Do you want to add this city to your list?';
  dataInSearchField: string = '';
  @Output() currentLocationEvent = new EventEmitter<string>();
  @ViewChild('auto') auto: any;

  constructor(private locSer: LocationService, private sharedDs: SharedDataService, private weatherDS: WeatherService) { }

  ngOnInit(): void {
    this.locSer.getAllAvilableLocationData().subscribe((resp) => {
      this.sharedDs.AllLocationDataResponse.next(resp.body);
      this.allLocationsData = resp.body;
    });
  }

  drop(event: CdkDragDrop<string[]>) {
    moveItemInArray(this.allLocationsData, event.previousIndex, event.currentIndex);
    if (event.previousIndex != event.currentIndex)
      this.locSer.updateLocationPosition(event.previousIndex, event.currentIndex).subscribe((reps) => {

      });
  }

  loadCurrentCityData(cityName: string) {
    this.currentLocationEvent.emit(cityName);
  }

  selectEvent(item: any) {
    this.loadCurrentCityData(item.cityName);
    this.auto.close();
    this.auto.clear();
  }

  onChangeSearch(val: string) {
    this.dataInSearchField = val;
  }

  addNewLocation() {
    const locationToAdd = this.dataInSearchField;
    this.weatherDS.getWeatheDataBasedOnLocation(locationToAdd).subscribe((resp) => {
      if (resp?.statusCode !== '1006') {
        this.locSer.createloc(resp.location.name).subscribe((respLoc) => {
          this.sharedDs.WeatherDataServiceResponse.next(resp);
          this.locSer.getAllAvilableLocationData().subscribe((resp) => {
            this.allLocationsData = resp.body;
          });
        });
      } else {
        console.error("Invalid location");
      }
    })
    this.auto.close();
    this.auto.clear();
  }

  deleteLocation(locationId: string) {
    this.locSer.deleteloc(locationId).subscribe((resp) => {
      this.locSer.getAllAvilableLocationData().subscribe((respAvl) => {
        this.sharedDs.AllLocationDataResponse.next(respAvl.body);
        this.allLocationsData = respAvl.body;
        if (this.allLocationsData && this.allLocationsData.length == 0) {
          this.allLocationsData = [];
        }
        this.weatherDS.getWeatheDataBasedOnLocation(this.allLocationsData?.[0].cityName || 'INDIA').subscribe((respWd) => {
          this.sharedDs.WeatherDataServiceResponse.next(respWd);
        });
      }, error => {
        this.allLocationsData = [];
      });
    });
  }
}
