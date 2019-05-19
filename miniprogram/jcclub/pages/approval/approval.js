//获取应用实例
const app = getApp()

Page({

  /**
   * 页面的初始数据
   */
  data: {
    teams: {},
    visible: false,
    remark: null,
    postId: null
  },
  onLoad: function(){
    var that = this;
    wx.request({
      url: app.globalData.requestUri + '/personal?actionName=queryPost',
      success: function (res) {
        that.setData({
          teams: res.data.data
        })
      }
    })
  },
  approval: function(event){
    console.log(event.currentTarget.dataset)
    var that = this;
    var postId = event.currentTarget.dataset.postid;
    var approvalStatus = event.currentTarget.dataset.approvalstatus;
    wx.request({
      url: app.globalData.requestUri + '/personal?actionName=approval&postId=' + postId + '&approvalStatus=' + approvalStatus,
      success: function (res) {
        wx.showToast({
          title: '审批成功',
          icon: 'none',
          duration: 2000,
          mask: true
        }),
          that.onLoad();
      }
    })
  },
  approvalReject: function () {
    console.log(this.data.postId)
    console.log(this.data.remark)
    var that = this;
    that.handleClose()
    wx.request({
      url: app.globalData.requestUri + '/personal?actionName=approval&postId=' + that.data.postId + '&approvalStatus=2&remark=' + that.data.remark,
      success: function (res) {
        wx.showToast({
          title: '审批成功',
          icon: 'none',
          duration: 2000,
          mask: true
        }),
          that.onLoad();
      }
    })
  },
  openModel: function(event){
    var that = this;
    that.setData({
      postId: event.currentTarget.dataset.postid,
      visible: true
    })
  },
  handleClose() {
    this.setData({
      visible: false
    });
    console.log(this.data.visible)
  },
  keyInput: function (e) {
    var that = this;
    console.log(e)
    that.setData({
      remark: e.detail.detail.value
    })
  }


})