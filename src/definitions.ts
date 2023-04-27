import type {
  SetContactOptions
} from './interfaces/base';
import type { PluginListenerHandle } from '@capacitor/core';

import type { PushMessageEvent, TokenResult } from './interfaces/push';
import type { ITokenInitializationStatus, PushMessageDTO, UserInformationDTO } from './interfaces/pushAndroid';

export interface EmarsysSDKCustomPlugin {
  
  echo(options: { value: string }): Promise<{ value: string }>;
  
  addListener(
    eventName: 'pushMessageEvent',
    listenerFunc: (event: PushMessageEvent) => void
  ): Promise<PluginListenerHandle> & PluginListenerHandle;

  getUUID(value: string): Promise<{ value: string }>;

  
  requestPermissions(): Promise<PermissionStatus>;

  checkPermissions(): Promise<PermissionStatus>;

  setContact(options: SetContactOptions): Promise<void>;

  getPushToken(): Promise<TokenResult>;

  register(): Promise<TokenResult>;

  checkPermissions(): Promise<PermissionStatus>;

  clearContact(options: SetContactOptions): Promise<void>;


  //----for android
  setPushTokenFirebase(data: {
    value: string;
  }): Promise<ITokenInitializationStatus>;

  
  setPushMessage(data: PushMessageDTO): Promise<{ value: PushMessageDTO }>;
  getUserInfo(data: UserInformationDTO): Promise<{ value: unknown }>;

  setUser(data: {
    value: string;
  }): Promise<void>;

  clearUser():Promise<void>;
// ---------------------------

}
