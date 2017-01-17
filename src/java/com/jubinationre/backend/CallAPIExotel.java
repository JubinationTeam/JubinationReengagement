/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jubinationre.backend;

import com.jubinationre.model.pojo.CallAPIMessage;
import com.jubinationre.service.AdminMaintainService;
import java.io.IOException;
import java.io.StringReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.SynchronousQueue;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 *
 * @author Welcome
 */
@Component
public class CallAPIExotel {
                    private boolean flag=false;
                    private boolean checkFlag=false;
                    private int count = 0;
                    private String caller;
                    private String appId;
                    private ConcurrentLinkedQueue<String> numbers = new ConcurrentLinkedQueue<>();
                    private ConcurrentLinkedQueue<String> sids = new ConcurrentLinkedQueue<>();
                    private ConcurrentLinkedQueue<CallAPIMessage> stageThreeUpdates = new ConcurrentLinkedQueue<>();

                    @Autowired
                    AdminMaintainService adminMaintain;

                    @Async
                    @Scheduled(fixedRate=15000)
                    void callDicyCustomer() throws IOException,InterruptedException, JAXBException{
                                            if(flag&&!numbers.isEmpty()){
                                                                    String callerId=numbers.peek();
                                                                    String responseText="NA";
                                                                    Document doc=null;
                                                                    System.out.println("Stage 1:"+numbers.size()+" Sids in queue to be sent to exotel to process "+Thread.currentThread());
                                                                    if(callerId!=null){
                                                                                            CloseableHttpResponse response=null;
                                                                                            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                                                                                            DocumentBuilder builder;
                                                                                            InputSource is;
                                                                                            try { 
                                                                                                                        //requesting exotel to initiate call
                                                                                                                        CloseableHttpClient httpclient = HttpClients.createDefault();
                                                                                                                        HttpPost httpPost = new HttpPost("https://jubination:ce5e307d58d8ec07c8d8456e42ed171ff8322fd0@twilix.exotel.in/v1/Accounts/jubination/Calls/connect");
                                                                                                                        List<NameValuePair> formparams = new ArrayList<NameValuePair>();
                                                                                                                        formparams.add(new BasicNameValuePair("From",callerId));
                                                                                                                        //formparams.add(new BasicNameValuePair("To",callerId));
                                                                                                                        formparams.add(new BasicNameValuePair("CallerId",caller));
                                                                                                                        formparams.add(new BasicNameValuePair("CallerType","trans"));
                                                                                                                        formparams.add(new BasicNameValuePair("Url","http://my.exotel.in/exoml/start/"+appId));
                                                                                                                        formparams.add(new BasicNameValuePair("TimeLimit","1800"));
                                                                                                                        formparams.add(new BasicNameValuePair("TimeOut","30"));
                                                                                                                        formparams.add(new BasicNameValuePair("SatusCallback",""));
                                                                                                                        formparams.add(new BasicNameValuePair("CustomField","testcall"));
                                                                                                                        UrlEncodedFormEntity uEntity = new UrlEncodedFormEntity(formparams, Consts.UTF_8);
                                                                                                                        httpPost.setEntity(uEntity);
                                                                                                                        response = httpclient.execute(httpPost);
                                                                                                                        System.out.println("Stage 1:Number sent to exotel");
                                                                                                                        HttpEntity entity = response.getEntity();

                                                                                                                        responseText = EntityUtils.toString(entity, "UTF-8");
                                                                                                                        builder = factory.newDocumentBuilder();
                                                                                                                        is = new InputSource(new StringReader(responseText));
                                                                                                                        doc = builder.parse(is);
                                                                                                                        doc.getDocumentElement().normalize();
                                                                                            } 
                                                                                            catch(IOException | ParseException | ParserConfigurationException | SAXException | DOMException e){
                                                                                                                        e.printStackTrace();
                                                                                            }
                                                                                            finally {
                                                                                                                        if(response!=null){
                                                                                                                                                response.close();
                                                                                                                        }
                                                                                           }
                                                                    }
                                                                    //parsing xml response from exotel
                                                                    JAXBContext jaxbContext = JAXBContext.newInstance(ExotelMessage.class);
                                                                    Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
                                                                    ExotelMessage eMessage = (ExotelMessage) jaxbUnmarshaller.unmarshal(doc);
                                                                    CallAPIMessage message=null;
                                                                    System.out.println("Stage 1:Got xml message");
                                                                    if(eMessage!=null){
                                                                                            System.out.println("Stage 1:xml message not null");
                                                                                            if(eMessage.getSuccessMessage()!=null){
                                                                                                                System.out.println("Stage 1:xml message success");
                                                                                                                message=eMessage.getSuccessMessage();
                                                                                                                message.setTrackStatus("Call request sent");
                                                                                                                message.setMessage("Stage 1 Calling");
                                                                                                                synchronized(this){
                                                                                                                                //adding all the sid values of calls placed
                                                                                                                                    sids.offer(message.getSid());
                                                                                                                }
                                                                                             }
                                                                                            else if (eMessage.getFailureMessage()!=null){
                                                                                                               System.out.println("Stage 1:xml message failed");
                                                                                                                message=eMessage.getFailureMessage();
                                                                                                                message.setTo(callerId);
                                                                                                                message.setDateCreated(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
                                                                                                                message.setTrackStatus(message.getMessage());
                                                                                            }
                                                                                            else{
                                                                                                                System.out.println("Stage 1:xml message unknown error");
                                                                                                                message.setTo(callerId);
                                                                                                                message.setTrackStatus("Unknown Error");
                                                                                                                message.setDateCreated(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
                                                                                            }
                                                                    }
                                                                    System.out.println("Stage 1:adding message to database");
                                                                    //saving call details
                                                                    adminMaintain.addCallAPIMessage(message);
                                                                                            //removing number
                                                                                            numbers.poll();
                                                                                            System.out.println("Stage 1:Number out of queue");
                                                                    flag=!numbers.isEmpty();
                                                                    if(!flag){
                                                                        //initiate call status update after call are over
                                                                        checkFlag=true;
                                                                    }
                                            }
                    }

   
                    @Async
                    @Scheduled(fixedDelay=3000)
                    void checkCalledCustomers() throws IOException, JAXBException{
                                        if(checkFlag){
                                                            String sid=null;
                                                            String responseText="NA";
                                                            Document doc=null;
                                                            System.out.println("Stage 2:"+sids.size()+" Sids in queue to be sent to exotel to process "+Thread.currentThread());
                                                                                //fetching all the sid values
                                                                                sid=sids.peek();
                                                             CloseableHttpResponse response=null;
                                                             DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                                                             DocumentBuilder builder;
                                                             InputSource is;
                                                             try { 
                                                                         //checking sid details with exotel
                                                                         CloseableHttpClient httpclient = HttpClients.createDefault();
                                                                         HttpGet httpGet = new HttpGet("https://jubination:ce5e307d58d8ec07c8d8456e42ed171ff8322fd0@twilix.exotel.in/v1/Accounts/jubination/Calls/"+sid);
                                                                         response = httpclient.execute(httpGet);
                                                                         System.out.println("Stage 2:Sid sent to exotel");
                                                                         HttpEntity entity = response.getEntity();
                                                                         responseText = EntityUtils.toString(entity, "UTF-8");
                                                                         builder = factory.newDocumentBuilder();
                                                                         is = new InputSource(new StringReader(responseText));
                                                                         doc = builder.parse(is);
                                                                         doc.getDocumentElement().normalize();
                                                              } 
                                                             catch(IOException | ParseException | ParserConfigurationException | SAXException | DOMException e){
                                                                        e.printStackTrace();
                                                              }
                                                             finally {
                                                                        if(response!=null){
                                                                                            response.close();
                                                                        }
                                                              }
                                                             //parsing xml response from exotel
                                                             JAXBContext jaxbContext = JAXBContext.newInstance(ExotelMessage.class);
                                                             Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
                                                             ExotelMessage eMessage = (ExotelMessage) jaxbUnmarshaller.unmarshal(doc);
                                                             CallAPIMessage message=null;
                                                             System.out.println("Stage 2:Got xml message");
                                                             if(eMessage!=null){
                                                                            System.out.println("Stage 2:xml message not null");
                                                                            if(eMessage.getSuccessMessage()!=null){
                                                                                            message=eMessage.getSuccessMessage();
                                                                                            System.out.println("Stage 2:xml message success");
                                                                                            if(message.getStatus().equals("queued")||message.getStatus().equals("ringing")||message.getStatus().equals("in-progress")){
                                                                                                                        count++;
                                                                                                                        System.out.println("Stage 2:sid out of queue. Trying for"+count+"th time");
                                                                                            }
                                                                                            else {
                                                                                                                        CallAPIMessage storedMessage=adminMaintain.getCallRecordBySid(message.getSid());
                                                                                                                        if(storedMessage!=null){
                                                                                                                                                System.out.println("Stage 2:sid of the number was present already");
                                                                                                                                                 message.setOrderId(storedMessage.getOrderId());
                                                                                                                                                 message.setMessage("Stage 2 Tracking");
                                                                                                                                                //updating call details
                                                                                                                                                if(!storedMessage.getMessage().equals("Stage 3 Tracking")){
                                                                                                                                                                            System.out.println("Stage 2:stage 3 not updated yet");
                                                                                                                                                                            System.err.println(adminMaintain.updateCallAPIMessage(message)+" Message updated to database");
                                                                                                                                                  }
                                                                                                                                                  else{
                                                                                                                                                                            System.out.println("Stage 2:stage 3 updated already");
                                                                                                                                                                            storedMessage.setStatus(message.getStatus());
                                                                                                                                                                            storedMessage.setStatus(message.getCallType());
                                                                                                                                                                            storedMessage.setStatus(message.getRecordingUrl());
                                                                                                                                                                            System.err.println(adminMaintain.updateCallAPIMessage(storedMessage)+" Message updated to database");
                                                                                                                                                   }
                                                                                                                        }
                                                                                                                                                 //removing all the sid values
                                                                                                                                                sids.poll();
                                                                                                                                                System.out.println("Stage 2:Sid out of queue");
                                                                                            }
                                                                                            if(count>=300){
                                                                                                                        System.out.println("Stage 2:Time out. sid out of queue. Trying for next sid");
                                                                                                                        sids.poll();
                                                                                                                        count=0;
                                                                                            }

                                                                            }
                                                             }
                                                             //stop operation if all done
                                                             checkFlag=!sids.isEmpty();
                                        }
                    }

                    @Async
                    @Scheduled(fixedDelay=3000)
                    void checkStageThreeCustomers() throws IOException, JAXBException{
                                        if(!flag&&!checkFlag&&!stageThreeUpdates.isEmpty()){
                                             //fetching all the call values
                                                        CallAPIMessage callUpdated= stageThreeUpdates.peek();
                                                                           
                                                                           
                                                        if(callUpdated!=null){
                                                                    List<CallAPIMessage> callList=adminMaintain.getCallBySid(callUpdated.getSid());
                                                                    if(callList!=null&&!callList.isEmpty()){
                                                                                        CallAPIMessage  call=callList.get(0);
                                                                                          System.out.println("Stage 3:"+stageThreeUpdates.size()+"left. Update service started");
                                                                                         if(call!=null){
                                                                                                             System.out.println("Stage 3:Call message present in database already and not null");
                                                                                                             call.setMessage("Stage 3 Tracking");
                                                                                                             call.setStatus("completed");
                                                                                                             call.setDailCallDuration(callUpdated.getDailCallDuration());
                                                                                                             call.setCallType(callUpdated.getCallType());
                                                                                                             call.setTrackStatus(callUpdated.getTrackStatus());
                                                                                                             call.setDigits(callUpdated.getDigits());
                                                                                                             call.setDailWhomNumber(callUpdated.getDailWhomNumber());
                                                                                                             call.setRecordingUrl(callUpdated.getRecordingUrl());
                                                                                                             call.setStartTime(callUpdated.getStartTime());
                                                                                                             call.setDirection(callUpdated.getDirection());
                                                                                                             System.out.println("Stage 3:Updating database properties");
                                                                                                             adminMaintain.updateCallAPIMessage(call);
                                                                                                             synchronized(this){
                                                                                                                               //removing all the call values
                                                                                                                                stageThreeUpdates.poll();
                                                                                                                                System.out.println("Stage 3:Call Message out of queue");
                                                                                                            }
                                                                                         }
                                                                    }
                                                                    else{
                                                                                            System.out.println("Stage 3:Call message not present in database");
                                                                                            callUpdated.setMessage("Incoming Tracking");
                                                                                            adminMaintain.buildCallAPIMessage(callUpdated);
                                                                                                           //removing all the call values
                                                                                                            stageThreeUpdates.poll();
                                                                                                            System.out.println("Stage 3:Call Message out of queue");
                                                                                            System.out.println("Stage 3:Built new CallMessage");
                                                                    }
                                                        }
                                                        if(stageThreeUpdates.isEmpty()){
                                                            adminMaintain.sendEmailUpdate();
                                                        }
                                        }
                    }


                    public boolean isFlag() {
                                    return flag;
                    }

                    public void setFlag(boolean flag) {
                                    this.flag = flag;
                    }

    public ConcurrentLinkedQueue<String> getNumbers() {
        return numbers;
    }

    public ConcurrentLinkedQueue<String> getSids() {
        return sids;
    }

    public ConcurrentLinkedQueue<CallAPIMessage> getStageThreeUpdates() {
        return stageThreeUpdates;
    }

    public String getCaller() {
        return caller;
    }

    public void setCaller(String caller) {
        this.caller = caller;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

                   
        
}
