import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ConfirmDeleteClienteComponent } from './confirm-delete-cliente.component';

describe('ConfirmDeleteComponent', () => {
  let component: ConfirmDeleteClienteComponent;
  let fixture: ComponentFixture<ConfirmDeleteClienteComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ConfirmDeleteClienteComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ConfirmDeleteClienteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
