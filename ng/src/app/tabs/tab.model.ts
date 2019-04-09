
export class Tab {

  constructor(private _type: string, private _id: number) {}

  get type(): string {
    return this._type;
  }

  get id(): number {
    return this._id;
  }

  get active(): boolean {
    return (window.location.href.includes(this.type + '/' + String(this.id)));
  }

}
