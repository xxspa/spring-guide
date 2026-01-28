# 怎么把spring boot应用部署到kubernetes集群中

首先这只是一个普通的web项目，作为测试使用

- 首先打包镜像

```bash
./gradlew bootBuildImage
```

- 测试镜像是否能正常启动

```bash
docker run -p 8080:8080 spring-boot-kubernetes:1.0-SNAPSHOT
# 使用curl命令测试
curl localhost:8080/actuator/health

```

- 给镜像打标签并推送到远程仓库

```bash
docker tag springguides/demo
# 注意推送需要配置dockerhub的账号密码，由于是本地镜像，所以不推送也能正常进行后续步骤。
docker push springguides/demo
```

- 创建kubernetes部署文件`deployment.yaml`

```bash
kubectl create deployment demo --image=springguides/demo --dry-run -o=yaml > deployment.yaml
echo --- >> deployment.yaml
kubectl create service clusterip demo --tcp=8080:8080 --dry-run -o=yaml >> deployment.yaml
```

- 部署到kubernetes集群

```bash
kubectl apply -f deployment.yaml
```

你应该看见以下输出

deployment.apps/demo created
service/demo created

- 检查pod是否启动成功

```bash
kubectl get all
```

└> kubectl get all
NAME READY STATUS RESTARTS AGE
pod/demo-8594f487dd-qfw6x 1/1 Running 0 12m

NAME TYPE CLUSTER-IP EXTERNAL-IP PORT(S)    AGE
service/demo ClusterIP 10.111.196.93   <none>        8080/TCP 12m
service/kubernetes ClusterIP 10.96.0.1       <none>        443/TCP 23m

NAME READY UP-TO-DATE AVAILABLE AGE
deployment.apps/demo 1/1 1 1 12m

NAME DESIRED CURRENT READY AGE
replicaset.apps/demo-8594f487dd 1 1 1 12m

看到以上显示就是运行成功了

- 访问服务，创建端口转发

```bash
kubectl port-forward svc/demo 8080:8080
curl localhost:8080/actuator/health
{"status":"UP"}
```