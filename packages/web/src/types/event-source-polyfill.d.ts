// types/event-source-polyfill.d.ts
declare module 'event-source-polyfill' {
    export class EventSourcePolyfill {
      constructor(url: string, configuration?: any);
      onmessage: (event: MessageEvent) => void;
      onerror: (event: Event) => void;
      onopen: (event: Event) => void;
      close: () => void;
    }
  }