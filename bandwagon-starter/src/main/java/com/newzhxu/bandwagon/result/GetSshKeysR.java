package com.newzhxu.bandwagon.result;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class GetSshKeysR extends BaseR {
    private Long error;
    @JsonProperty("ssh_keys_veid")
    private String sshKeysVeid;
    @JsonProperty("ssh_keys_user")
    private String sshKeysUser;
    @JsonProperty("ssh_keys_preferred")
    private String sshKeysPreferred;
    @JsonProperty("shortened_ssh_keys_veid")
    private String shortenedSshKeysVeid;
    @JsonProperty("shortened_ssh_keys_user")
    private String shortenedSshKeysUser;
    @JsonProperty("shortened_ssh_keys_preferred")
    private String shortenedSshKeysPreferred;

}
