import { reactive } from "vue";

export const userInfo = reactive({
  uid: "",
  username: "",
  avatarUrl: "",
  phoneNumber: "",
  emil: "",
});
export const userInfoList = reactive({
  pages: 0,
  size: 0
})
export function saveUserInfo() {
  localStorage.setItem("userInfo", JSON.stringify(userInfo));
}
export function initUserInfo() {
  const temp = JSON.parse(localStorage.getItem("userInfo"), userInfo);
  userInfo.uid = temp.uid
  userInfo.username = temp.username
  userInfo.avatarUrl = temp.avatarUrl
  userInfo.phoneNumber = temp.phoneNumber
  userInfo.emil = temp.emil
}
export function removeUserInfo() {
  userInfo.uid = "";
  userInfo.username = "";
  userInfo.avatarUrl = "";
  userInfo.phoneNumber = "";
  userInfo.emil = "";
  console.log(userInfo);
  saveUserInfo();
}
