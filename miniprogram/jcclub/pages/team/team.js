//获取应用实例
const app = getApp()

// pages/team/team.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
      teams:{}
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
      var that = this;
      wx.request({
        url: app.globalData.requestUri + '/teamLibrary?actionName=list&userInfoId=' + app.globalData.userId,
          success: function (res) {
              that.setData({
                  teams:res.data.data
              })
          }
      })
  },
  changeData: function () {
    this.onLoad();//最好是只写需要刷新的区域的代码，onload也可，效率低，有点low
  },

  toPublish: function(){
    wx.navigateTo({
      url: '/pages/teamInfo/teamInfo'
    })
  },

  delivery: function (event) {
    console.log(event.currentTarget.dataset)
    wx.request({
      url: app.globalData.requestUri + '/teamLibrary?actionName=delivery&userInfoId=' + app.globalData.userId + '&teamId=' + event.currentTarget.dataset.teamid,
      success: function (res) {
        if (res.data.resCode == '200') {
          if(res.data.data === 1){
              wx.showToast({
                  title: '请先前往个人中心/我的信息,完善资料才能投递',
                icon: 'none',
                duration: 2000,
                  mask: true
              })
          }else if(res.data.data === 2){
              wx.showToast({
                  title: '不能投递自己的团队',
                  icon: 'none',
                duration: 2000,
                  mask: true
              })
          }else if(res.data.data === 3){
              wx.showToast({
                  title: '您已经投递,无需重复投递',
                icon: 'none',
                duration: 2000,
                  mask: true
              })
          }else if(res.data.data === 4){
              wx.showToast({
                  title: '投递成功,请耐心等候团队负责人联系',
                  icon: 'succes',
                  duration: 2000,
                  mask: true
              })
          }
        }
      }
    })
  },

})