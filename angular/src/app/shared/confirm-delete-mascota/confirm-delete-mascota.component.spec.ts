import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ConfirmDeleteMascotaComponent } from './confirm-delete-mascota.component';

describe('ConfirmDeleteMascotaComponent', () => {
  let component: ConfirmDeleteMascotaComponent;
  let fixture: ComponentFixture<ConfirmDeleteMascotaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ConfirmDeleteMascotaComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ConfirmDeleteMascotaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
