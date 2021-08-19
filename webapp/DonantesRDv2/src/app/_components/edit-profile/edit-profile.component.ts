import { MapsAPILoader } from '@agm/core';
import { Component, ElementRef, NgZone, OnDestroy, OnInit, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { DonationAppointment } from 'src/app/_models/donation-apointment.model';
import { User } from 'src/app/_models/user.model';
import { ResourceService } from 'src/app/_services/resource.service';
import { TokenStorageService } from 'src/app/_services/token-storage.service';
import { UserService } from 'src/app/_services/user.service';

@Component({
  selector: 'app-edit-profile',
  templateUrl: './edit-profile.component.html',
  styleUrls: ['./edit-profile.component.css']
})
export class EditProfileComponent implements OnInit, OnDestroy {

  constructor(private token: TokenStorageService, private userService: UserService, private resource: ResourceService, private router: Router, private mapsAPILoader: MapsAPILoader, private ngZone: NgZone) { }
  
  private geoCoder: any;

  zoom = 17;

  @ViewChild('search')
  public searchElementRef!: ElementRef;

  ngOnDestroy(): void {
    this.subscriptions.unsubscribe();
  }

  user!: User;
  subscriptions: Subscription = new Subscription();
  bloodGroups: String[] = this.resource.getBloodGroups();


  ngOnInit(): void {
    this.user = new User().deserialize(this.token.getUser());

    this.mapsAPILoader.load().then(() => {
      this.geoCoder = new google.maps.Geocoder;
      let autocomplete = new google.maps.places.Autocomplete(this.searchElementRef.nativeElement, {componentRestrictions: {country: "DO"}});
      autocomplete.addListener("place_changed", () => {
        this.ngZone.run(() => {
          let place: google.maps.places.PlaceResult = autocomplete.getPlace();
          if (place.geometry === undefined || place.geometry === null) {
            return;
          }
          this.user.address = place.formatted_address || "";
          this.user.latitude = place.geometry.location.lat();
          this.user.longitude = place.geometry.location.lng();
          this.zoom = 17;
        });
      });
    });
  }

  saveProfile(){
    this.subscriptions.add(
      this.userService.saveProfile(this.user).subscribe(response => {
        this.router.navigate(["profile"]);
      })
    )
    this.token.saveUser(this.user);
    
  }

  markerDragEnd(ev: any){
    this.geoCoder.geocode({latLng: ev.latLng}, (results: any) => {
      this.ngZone.run(() => {
        this.user.address = results[0].formatted_address;
        this.user.latitude = ev.latLng.lat();
        this.user.longitude = ev.latLng.lng()
      })
    })
  }
}
