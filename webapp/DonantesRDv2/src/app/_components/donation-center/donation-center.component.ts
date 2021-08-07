import { MapsAPILoader } from '@agm/core';
import { Component, ElementRef, NgZone, OnDestroy, OnInit, ViewChild } from '@angular/core';
import { FormGroupDirective } from '@angular/forms';
import { ModalDismissReasons, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { DataTableDirective } from 'angular-datatables';
import { Subject, Subscription } from 'rxjs';
import { DonationCenter } from '../../_models/donation-center.model';
import { AdministrationService } from '../../_services/administration.service';
import { DonationCenterModalComponent } from '../_modals/donation-center-modal/donation-center-modal.component';

@Component({
  selector: 'app-donation-center',
  templateUrl: './donation-center.component.html',
  styleUrls: ['./donation-center.component.css']
})
export class DonationCenterComponent implements OnInit, OnDestroy{
  constructor(private modalService: NgbModal, private administrationService: AdministrationService) {}


  donationCenters: DonationCenter[] = [];

  

  subscriptions: Subscription = new Subscription();



  ngOnInit(): void {
    this.subscriptions.add( 
      this.administrationService.getDonationCenters().subscribe(response => {
        this.donationCenters = response;
      })
    );


    // this.mapsAPILoader.load().then(() => {
    //   this.geoCoder = new google.maps.Geocoder;
    //   let autocomplete = new google.maps.places.Autocomplete(this.searchElementRef.nativeElement);
    //   autocomplete.addListener("place_changed", () => {
    //     this.ngZone.run(() => {
    //       let place: google.maps.places.PlaceResult = autocomplete.getPlace();
    //       if (place.geometry === undefined || place.geometry === null) {
    //         return;
    //       }
    //       this.latitude = place.geometry.location.lat();
    //       this.longitude = place.geometry.location.lng();
    //       this.zoom = 12;
    //     });
    //   });
    // });
  }

  rerender(): void {
    this.donationCenters = [...this.donationCenters];
  }

   ngOnDestroy(): void {
    this.subscriptions.unsubscribe();
  }

  open(){
    let ref = this.modalService.open(DonationCenterModalComponent, {size: 'lg'});
    this.subscriptions.add(
      ref.componentInstance.newCenter.subscribe((p: DonationCenter) => {
        this.newDonationCenter(p)
      })
    )
  }

  newDonationCenter(model: DonationCenter){
    if(model.id){
      this.subscriptions.add(
        this.administrationService.updateDonationCenter(model).subscribe()
      )
    }else{
      this.subscriptions.add(
        this.administrationService.createDonationCenter(model).subscribe(response => {
          model.id = response.id;
          this.donationCenters.push(model);
          this.rerender();
        })
      );
    }
    this.modalService.dismissAll();
  }

  update(center: DonationCenter){
    let ref = this.modalService.open(DonationCenterModalComponent, {size: 'lg'});
    ref.componentInstance.model = center;
    this.subscriptions.add(
      ref.componentInstance.newCenter.subscribe((p: DonationCenter) => {
        this.newDonationCenter(p)
      })
    )
  }

  remove(center: DonationCenter){
    this.donationCenters.forEach((item, idx) => {
      if(item.id == center.id) this.donationCenters.splice(idx, 1);
    });
    this.subscriptions.add(
      this.administrationService.deleteDonationCenter(center).subscribe()
    )
    this.rerender();
  }
}
