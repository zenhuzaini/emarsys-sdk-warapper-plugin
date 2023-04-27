import { registerPlugin } from '@capacitor/core';

import type { EmarsysSDKCustomPlugin } from './definitions';

const EmarsysSDKCustom = registerPlugin<EmarsysSDKCustomPlugin>('EmarsysSDKCustom', {
  web: () => import('./web').then(m => new m.EmarsysSDKCustomWeb()),
});

export * from './definitions';
export { EmarsysSDKCustom };
