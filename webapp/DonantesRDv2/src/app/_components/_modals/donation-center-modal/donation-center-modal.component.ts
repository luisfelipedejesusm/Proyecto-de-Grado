import { MapsAPILoader } from '@agm/core';
import { Component, ElementRef, EventEmitter, Input, NgZone, OnInit, Output, ViewChild } from '@angular/core';
import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { Subscription } from 'rxjs';
import { DonationCenter } from 'src/app/_models/donation-center.model';
import { AdministrationService } from 'src/app/_services/administration.service';

@Component({
  selector: 'app-donation-center-modal',
  templateUrl: './donation-center-modal.component.html',
  styleUrls: ['./donation-center-modal.component.css']
})
export class DonationCenterModalComponent implements OnInit {

  constructor(private modalService: NgbModal, private mapsAPILoader: MapsAPILoader, private ngZone: NgZone) { }

  @ViewChild('search')
  public searchElementRef!: ElementRef;

  
  subscriptions: Subscription = new Subscription();

  ngOnInit(): void {
    this.mapsAPILoader.load().then(() => {
      this.geoCoder = new google.maps.Geocoder;
      let autocomplete = new google.maps.places.Autocomplete(this.searchElementRef.nativeElement, {componentRestrictions: {country: "DO"}});
      autocomplete.addListener("place_changed", () => {
        this.ngZone.run(() => {
          let place: google.maps.places.PlaceResult = autocomplete.getPlace();
          if (place.geometry === undefined || place.geometry === null) {
            return;
          }
          this.model.address = place.formatted_address || "";
          this.model.latitude = place.geometry.location.lat();
          this.model.longitude = place.geometry.location.lng();
          this.zoom = 17;
        });
      });
    });
  }

  @Output()
  newCenter: EventEmitter<DonationCenter> = new EventEmitter<DonationCenter>();

  @Input()
  model = new DonationCenter(); 

  // latitude: number = 18.5132572;
  // longitude: number = -69.8851534;
  zoom: number = 17;

  private geoCoder: any;

  newDonationCenter(){
    this.newCenter.emit(this.model);
  }

  clear(){
    this.modalService.dismissAll();
  }

  markerDragEnd(ev: any){
    this.geoCoder.geocode({latLng: ev.latLng}, (results: any) => {
      this.ngZone.run(() => {
        this.model.address = results[0].formatted_address;
      })
    })
  }

}
