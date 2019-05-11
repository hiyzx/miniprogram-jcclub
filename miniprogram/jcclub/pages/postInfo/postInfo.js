//获取应用实例
const app = getApp()

Page({

  /**
   * 页面的初始数据
   */
  data: {
    postInfo: {
    },
    teamId:null
  },

  onLoad: function (options) {
    var that = this;
    wx.request({
      url: app.globalData.requestUri + '/teamLibrary?actionName=queryTeamInfo&userInfoId=' + app.globalData.userId,
      success: function (res) {
        console.log(res)
        that.setData({
          teamId: res.data.data.id
        })
      }
    })
    console.log(that.data.teamId)
  },

  publish: function () {
    console.log(app.globalData.userId)
    var that = this;
    wx.request({
      url: app.globalData.requestUri + '/teamLibrary?actionName=publish&userInfoId=' + app.globalData.userId + '&teamId=' + that.data.teamId,
      data: this.data.postInfo,
      success: function (res) {
        if (res.data.resCode == '200') {
          wx.showToast({
            title: '保存成功',
            icon: 'succes',
            duration: 1000,
            mask: true
          })
          that.changeParentData();
        }
      }
    })
  },

  changeParentData: function () {
    var pages = getCurrentPages();//当前页面栈
    if (pages.length > 1) {
      var beforePage = pages[pages.length - 2];//获取上一个页面实例对象
      beforePage.changeData();//触发父页面中的方法
    }
    wx.navigateBack({
      delta: 1
    });
  },

  keyInput: function (e) {
    var that = this;
    console.log(e)
    const propertyName = e.currentTarget.id;
    const postInfo = Object.assign({}, this.data.postInfo, { [propertyName]: e.detail.detail.value })
    console.log(propertyName)
    console.log(postInfo)
    that.setData({
      postInfo: postInfo
    })
    console.log(postInfo)
  },
  toTeamInfo: function () {
    wx.navigateTo({
      url: '/pages/teamInfo/teamInfo'
    })
  },


})