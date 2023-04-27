export interface ITokenInitializationStatus {
  status: string;
  isError: boolean;
}

export interface PushMessageDTO {
  title: string;
  text: string;
  image?: string;
}

export interface UserInformationDTO {
  firebaseToken?: string;
  userId: string;
}
