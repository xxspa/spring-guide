package com.newzhxu.agent;

import com.sun.tools.attach.AgentInitializationException;
import com.sun.tools.attach.AgentLoadException;
import com.sun.tools.attach.AttachNotSupportedException;
import com.sun.tools.attach.VirtualMachine;

import java.io.IOException;

public class Attach {
    public static void main(String[] args) throws IOException, AttachNotSupportedException {
        VirtualMachine vm = VirtualMachine.attach("638793");
        try {
            vm.loadAgent("bytebuddy/agent/build/libs/agent.jar");
        } catch (AgentLoadException | AgentInitializationException e) {
            throw new RuntimeException(e);
        } finally {
            vm.detach();
        }
    }
}
