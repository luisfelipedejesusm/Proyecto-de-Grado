import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DonationCenterModalComponent } from './donation-center-modal.component';

describe('DonationCenterModalComponent', () => {
  let component: DonationCenterModalComponent;
  let fixture: ComponentFixture<DonationCenterModalComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DonationCenterModalComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DonationCenterModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
